package org.portfolio.user.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String aboutMe;
    private String workExperience;
    private String accademics;
}
