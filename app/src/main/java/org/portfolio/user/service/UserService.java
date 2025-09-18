package org.portfolio.user.service;

import org.hibernate.jdbc.Work;
import org.portfolio.user.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    public UserResponseDto createUser(UserRequestDto userDto);

    public UserResponseDto updateUser(String username, UserUpdateDto userDto, MultipartFile profilePicture);

    public LoginResponseDto loginUser(String username, String password);

    public UserResponseDto getUser(String username);

    public List<WorkExperienceDto> getWorkExperience(String username);

    public WorkExperienceDto addOrUpdateWorkExperience(String username, WorkExperienceDto workExperienceDto, Long id);

    public void deleteWorkExperience(String username, Long  id);
}
