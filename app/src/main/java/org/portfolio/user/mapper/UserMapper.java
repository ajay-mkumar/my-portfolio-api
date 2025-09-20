package org.portfolio.user.mapper;

import org.portfolio.user.dto.UserRequestDto;
import org.portfolio.user.dto.UserResponseDto;
import org.portfolio.user.dto.UserUpdateDto;
import org.portfolio.user.modal.User;

public class UserMapper {
    public static User toEntity(UserRequestDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setAboutMe(userDto.getAboutMe());
        user.setWorkExperience(userDto.getWorkExperience());
        user.setAccademics(userDto.getAccademics());
        user.setPassword(userDto.getPassword()); // encode happens in createUser
        user.setGithub(userDto.getGithub());
        user.setPhone(userDto.getPhone());
        user.setLinkedIn(userDto.getLinkedIn());
        return user;
    }

    public static UserResponseDto toDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setAboutMe(user.getAboutMe());
        dto.setProfilePicture(user.getProfilePicture());
        dto.setResume(user.getResume());
        dto.setWorkExperience(user.getWorkExperience());
        dto.setAccademics(user.getAccademics());
        dto.setGithub(user.getGithub());
        dto.setPhone(user.getPhone());
        dto.setLinkedIn(user.getLinkedIn());
        return dto;
    }

    public static void updateEntity(User user, UserUpdateDto dto) {
        if (dto.getFirstName() != null) user.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) user.setLastName(dto.getLastName());
        if (dto.getUsername() != null) user.setUsername(dto.getUsername());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (dto.getAboutMe() != null) user.setAboutMe(dto.getAboutMe());
        if (dto.getProfilePicture() != null) user.setProfilePicture(dto.getProfilePicture());
        if (dto.getResume() != null) user.setResume(dto.getResume());
        if (dto.getWorkExperience() != null) user.setWorkExperience(dto.getWorkExperience());
        if (dto.getAccademics() != null) user.setAccademics(dto.getAccademics());
        if (dto.getGithub() != null) user.setGithub(dto.getGithub());
        if (dto.getPhone() != null) user.setPhone(dto.getPhone());
        if (dto.getLinkedIn() != null) user.setLinkedIn(dto.getLinkedIn());
    }

}
