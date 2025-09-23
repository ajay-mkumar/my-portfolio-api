package org.portfolio.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateDto {
    @Size(max=50)
    private String firstName;

    @Size(max=50)
    private String lastName;

    @Size(max=50)
    private String username;

    @Email
    private String email;

    @Size(max = 1000)
    private String aboutMe;

    private String profilePicture;

    private String resume;

    @Size(max = 1000)
    private String workExperience;

    @Size(max = 1000)
    private String accademics;

    private String github;

    private String phone;


    private String linkedIn;
}

