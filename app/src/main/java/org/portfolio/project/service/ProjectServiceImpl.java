package org.portfolio.project.service;

import lombok.RequiredArgsConstructor;
import org.portfolio.project.dto.ProjectDetailsDto;
import org.portfolio.exception.NoProjectFoundException;
import org.portfolio.project.mapper.ProjectMapper;
import org.portfolio.project.modal.Project;
import org.portfolio.user.modal.User;
import org.portfolio.project.repository.ProjectRepository;
import org.portfolio.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public ProjectDetailsDto addProject(String username, ProjectDetailsDto projectDetailsDto, MultipartFile photo) {
        User user = this.getUser(username);
        String imagePath = this.uploadPhoto(photo);
        projectDetailsDto.setImage(imagePath);
        Project project = ProjectMapper.toEntity(projectDetailsDto, user);
        Project createdProject = projectRepository.save(project);
        return ProjectMapper.toDto(createdProject);
    }

    public ProjectDetailsDto updateProject(String username, ProjectDetailsDto projectDetailsDto, Long id) {
        User user = this.getUser(username);

        Project project = getProjectById(id);

        if (!project.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not authroized to update this project");
        }

        project.setName(projectDetailsDto.getName());
        project.setImage(projectDetailsDto.getImage());
        project.setDescription(projectDetailsDto.getDescription());
        project.setTechStacks(projectDetailsDto.getTechStacks());
        project.setGithubLink(projectDetailsDto.getGithubLink());
        project.setAppLink(projectDetailsDto.getAppLink());
        Project updatedProject = projectRepository.save(project);
        return ProjectMapper.toDto(updatedProject);
    }

    public List<ProjectDetailsDto> getProjects(String username) {
        User user = getUser(username);
        List<Project> projects = projectRepository.findByUser(user);
        return projects.stream().map(ProjectMapper::toDto).toList();
    }

    public void deleteProject(String username, Long id) {
        User user = this.getUser(username);

        Project project = getProjectById(id);

        if (!project.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not authorized to delete this project");
        }

        projectRepository.delete(project);
    }

    private String uploadPhoto(MultipartFile photo) {
        try {
            File directory = new File(uploadDir);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            String fileName = System.currentTimeMillis() + "_" + photo.getOriginalFilename();

            photo.transferTo(new File(directory, fileName));

            return "/uploads/project/" + fileName;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new NoProjectFoundException("Project not found with the id " + id));
    }

    private User getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
