package org.portfolio.skills.modal;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.portfolio.user.modal.User;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Skills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Size(max = 1000, message = "Skill set must not exceed 1000")
    @NotBlank(message = "Skill set is required")
    private String skillSets;
}
