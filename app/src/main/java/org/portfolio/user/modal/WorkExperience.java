package org.portfolio.user.modal;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WorkExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @NotBlank(message = "Designation is required")
    @Size(max = 100, message = "Designation must not exceed 1000")
    private String designation;

    @NotBlank(message = "Duration is required")
    @Size(max = 100, message = "Duration must not exceed 1000")
    private String duration;

    @NotBlank(message = "Work details is required")
    @Size(max = 1000, message = "Work details must not exceed 1000")
    private String workDetails;
}
