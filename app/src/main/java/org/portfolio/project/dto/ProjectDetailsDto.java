package org.portfolio.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectDetailsDto {
    private Long id;

    @NotBlank(message = "Project name is required")
    @Size(max = 100, message = "Project name should not exceed 100")
    private String name;

    @NotBlank(message = "Image is required")
    private String image;

    @NotBlank(message = "Description is required")
    @Size(max = 1000, message = "Description should not exceed 1000")
    private String description;

    @NotBlank(message = "Tech stacks are required")
    private String techStacks;

    @NotBlank(message = "github link is required")
    private String githubLink;

    @NotBlank(message = "app link is required")
    private String appLink;
}
