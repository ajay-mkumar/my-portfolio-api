package org.portfolio.project.mapper;

import org.portfolio.project.dto.ProjectDetailsDto;
import org.portfolio.project.modal.Project;
import org.portfolio.user.modal.User;

public class ProjectMapper {

    public static Project toEntity(ProjectDetailsDto dto, User user) {
        if (dto == null) return null;

        Project project = new Project();
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        project.setImage(dto.getImage());
        project.setTechStacks(dto.getTechStacks());
        project.setGithubLink(dto.getGithubLink());
        project.setAppLink(dto.getAppLink());
        project.setUser(user);

        return project;
    }

    public static ProjectDetailsDto toDto(Project project) {
        if (project == null) return null;

        ProjectDetailsDto dto = new ProjectDetailsDto();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setTechStacks(project.getTechStacks());
        dto.setGithubLink(project.getGithubLink());
        dto.setAppLink(project.getAppLink());
        dto.setImage(project.getImage());

        return dto;
    }
}
