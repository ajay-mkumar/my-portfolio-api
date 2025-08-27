package org.portfolio.user.mapper;

import org.portfolio.user.dto.UserRequestDto;
import org.portfolio.user.dto.UserResponseDto;
import org.portfolio.user.modal.User;

public class UserMapper {
    public static User toEntity (UserRequestDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setAboutMe(userDto.getAboutMe());
        user.setWorkExperience(userDto.getWorkExperience());
        user.setAccademics(userDto.getAccademics());
        user.setPassword(userDto.getPassword()); // encode happens in createUser
        return user;
    }

    public static UserResponseDto toDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setAboutMe(user.getAboutMe());
        dto.setWorkExperience(user.getWorkExperience());
        dto.setAccademics(user.getAccademics());
        return dto;
    }
}
