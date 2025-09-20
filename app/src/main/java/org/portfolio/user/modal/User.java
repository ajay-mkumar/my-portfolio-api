package org.portfolio.user.modal;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String aboutMe;
    private String profilePicture;
    private String resume;
    private String workExperience;
    private String accademics;

    @Nullable
    private String github;

    private String phone;

    @Nullable
    private String linkedIn;

    private String role;
}
