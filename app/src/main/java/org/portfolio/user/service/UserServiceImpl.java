package org.portfolio.user.service;

import org.hibernate.jdbc.Work;
import org.portfolio.user.dto.*;
import org.portfolio.user.mapper.UserMapper;
import org.portfolio.user.mapper.WorkExperienceMapper;
import org.portfolio.user.modal.User;
import org.portfolio.user.modal.WorkExperience;
import org.portfolio.user.repository.UserRepository;
import org.portfolio.security.JwtService;
import org.portfolio.user.repository.WorkRepository;
import org.portfolio.util.UploadFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserRepository userRepository;
    private final WorkRepository workRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtUtil;

    private final String PROFILE_PICTURE = "profile_picture";
    private final String RESUME = "resume";

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtUtil, WorkRepository workRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.workRepository = workRepository;
    }

    public UserResponseDto createUser(UserRequestDto userDto, MultipartFile profilePicture, MultipartFile resume) {
        User user = UserMapper.toEntity(userDto);
        String profilePicturePath = UploadFileUtil.uploadFile(profilePicture, PROFILE_PICTURE);
        String resumePath = UploadFileUtil.uploadFile(resume, RESUME);
        user.setProfilePicture(profilePicturePath);
        user.setResume(resumePath);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // ✅ encode password
        return UserMapper.toDto(userRepository.save(user));
    }

    public UserResponseDto updateUser(String username, UserUpdateDto dto, MultipartFile profilePicture, MultipartFile resume) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        String profilePicturePath = UploadFileUtil.uploadFile(profilePicture, PROFILE_PICTURE);
        String resumePath = UploadFileUtil.uploadFile(resume, RESUME);
        dto.setProfilePicture(profilePicturePath);
        dto.setResume(resumePath);
        UserMapper.updateEntity(user, dto);
        return UserMapper.toDto(userRepository.save(user));
    }


    public UserResponseDto getUser(String username) {
        User user = fetchUser(username);
        return UserMapper.toDto(user);
    }

    public LoginResponseDto loginUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .filter(u -> passwordEncoder.matches(password, u.getPassword()))
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .build();

        String token = jwtUtil.generateToken(userDetails);
        return new LoginResponseDto(user.getUsername(), token);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    public WorkExperienceDto addOrUpdateWorkExperience(String username, WorkExperienceDto workExperienceDto, Long id) {
        // Check if update is intended
        if (id != null) {
            Optional<WorkExperience> optionalWorkExperience = workRepository.findById(id);

            if (optionalWorkExperience.isPresent()) {
                WorkExperience existingWorkExperience = optionalWorkExperience.get();

                // Ensure the work experience belongs to the given user
                if (existingWorkExperience.getUser().equals(fetchUser(username))) {
                    existingWorkExperience.setCompanyName(workExperienceDto.getCompanyName());
                    existingWorkExperience.setDesignation(workExperienceDto.getDesignation());
                    existingWorkExperience.setDuration(workExperienceDto.getDuration());
                    existingWorkExperience.setWorkDetails(workExperienceDto.getWorkDetails());

                    WorkExperience updatedWorkExperience = workRepository.save(existingWorkExperience);
                    return WorkExperienceMapper.toDto(updatedWorkExperience);
                } else {
                    throw new IllegalArgumentException("This work experience does not belong to the user: " + username);
                }
            } else {
                throw new IllegalArgumentException("Work experience with id " + id + " not found");
            }
        }

        // If id is null → create new WorkExperience
        WorkExperience newWorkExperience = WorkExperienceMapper.toEntity(workExperienceDto, fetchUser(username));
        WorkExperience createdWorkExperience = workRepository.save(newWorkExperience);
        return WorkExperienceMapper.toDto(createdWorkExperience);
    }

    public void deleteWorkExperience(String username, Long id) {
        User user = fetchUser(username);
        WorkExperience workExp = workRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));

        if (!workExp.getUser().equals(user)) {
            throw new RuntimeException("You do not have permission to delete this work experience");
        }

        // Delete the work experience
        workRepository.delete(workExp);
    }

    public List<WorkExperienceDto> getWorkExperience(String username) {
        List<WorkExperience> workExperience = workRepository.findByUser(fetchUser(username));
        return workExperience.stream()
                .map(WorkExperienceMapper::toDto) // convert entity -> DTO
                .collect(Collectors.toList());   // gather into List
    }


    private User fetchUser(String username) {
        return  userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }
}

