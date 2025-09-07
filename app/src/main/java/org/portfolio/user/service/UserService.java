package org.portfolio.user.service;

import org.hibernate.jdbc.Work;
import org.portfolio.user.dto.UserRequestDto;
import org.portfolio.user.dto.UserResponseDto;
import org.portfolio.user.dto.UserUpdateDto;
import org.portfolio.user.dto.WorkExperienceDto;

import java.util.List;

public interface UserService {
    public UserResponseDto createUser(UserRequestDto userDto);

    public UserResponseDto updateUser(String username, UserUpdateDto userDto);

    public String loginUser(String username, String password);

    public UserResponseDto getUser(String username);

    public List<WorkExperienceDto> getWorkExperience(String username);

    public WorkExperienceDto addOrUpdateWorkExperience(String username, WorkExperienceDto workExperienceDto, Long id);
}
