package org.portfolio.user.service;

import org.hibernate.jdbc.Work;
import org.portfolio.user.dto.UserRequestDto;
import org.portfolio.user.dto.UserResponseDto;
import org.portfolio.user.dto.WorkExperienceDto;

public interface UserService {
    public UserResponseDto createUser(UserRequestDto userDto);

    public String loginUser(String username, String password);

    public UserResponseDto getUser(String username);

    public WorkExperienceDto addWorkExperience(String username, WorkExperienceDto workExperienceDto);
}
