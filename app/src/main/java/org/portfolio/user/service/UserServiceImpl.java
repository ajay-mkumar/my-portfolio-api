package org.portfolio.user.service;

import org.portfolio.user.dto.UserRequestDto;
import org.portfolio.user.dto.UserResponseDto;
import org.portfolio.user.dto.WorkExperienceDto;
import org.portfolio.user.mapper.UserMapper;
import org.portfolio.user.mapper.WorkExperienceMapper;
import org.portfolio.user.modal.User;
import org.portfolio.user.modal.WorkExperience;
import org.portfolio.user.repository.UserRepository;
import org.portfolio.security.JwtService;
import org.portfolio.user.repository.WorkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserRepository userRepository;
    private final WorkRepository workRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtUtil;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtUtil, WorkRepository workRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.workRepository = workRepository;
    }

    public UserResponseDto createUser(UserRequestDto userDto) {
        User user = UserMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // ✅ encode password
        return UserMapper.toDto(userRepository.save(user));
    }

    public UserResponseDto getUser(String username) {
        User user = fetchUser(username);
        return UserMapper.toDto(user);
    }

    public String loginUser(String username, String password) {
        System.out.println("username: " + username);
        User user = userRepository.findByUsername(username)
                .filter(u -> passwordEncoder.matches(password, u.getPassword()))
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .build();

        return jwtUtil.generateToken(userDetails);

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

    public WorkExperienceDto addWorkExperience(String username, WorkExperienceDto workExperienceDto) {
        WorkExperience workExperience = WorkExperienceMapper.toEntity(workExperienceDto, fetchUser(username));
        WorkExperience createdWorkExperience = workRepository.save(workExperience);
        return WorkExperienceMapper.toDto(createdWorkExperience);
    }

    public WorkExperienceDto getWorkExperience(String username) {
        WorkExperience workExperience = workRepository.findByUser(fetchUser(username)).orElseThrow(() -> new RuntimeException("No work experience found"));
        return WorkExperienceMapper.toDto(workExperience);
    }

    private User fetchUser(String username) {
        return  userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }
}

