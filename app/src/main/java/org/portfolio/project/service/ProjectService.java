package org.portfolio.project.service;

import org.portfolio.project.dto.ProjectDetailsDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ProjectService {
    public ProjectDetailsDto addProject(String username, ProjectDetailsDto projectDetailsDto, MultipartFile photo);

    public List<ProjectDetailsDto> getProjects(String username);

    public ProjectDetailsDto updateProject(String username, ProjectDetailsDto projectDetailsDto, Long id);

    public void deleteProject(String username, Long id);
}
