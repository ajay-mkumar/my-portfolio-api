package org.portfolio.user.service;

import org.portfolio.user.dto.UserRequestDto;
import org.portfolio.user.dto.UserResponseDto;

public interface UserService {
    public UserResponseDto createUser(UserRequestDto userDto);

    public String loginUser(String username, String password);
}
