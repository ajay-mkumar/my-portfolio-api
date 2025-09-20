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
public class UserRequestDto {
    @NotBlank
    @Size(max=50, message = "First Name is required")
    private String firstName;

    @NotBlank
    @Size(max=50, message = "Last Name is required")
    private String lastName;

    @NotBlank
    @Size(max=50, message = "username is required")
    private String username;

    @NotBlank
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotBlank
    @Size(max = 1000, message = "About me should not exceed 1000")
    private String aboutMe;

    @NotBlank
    @Size(max = 1000, message = "work experience should not exceed 1000")
    private String workExperience;

    @NotBlank
    @Size(max = 1000, message = "Accademics should not exceed 1000")
    private String accademics;

    @NotBlank
    @Size(min=8, message = "Please enter at least 8 characters")
    private String password;

    private String github;

    @NotBlank(message = "Phone should not be blank")
    @Size(min = 10, max = 10, message = "Phone must contain only 10 digits")
    private String phone;

    private String linkedIn;
}

