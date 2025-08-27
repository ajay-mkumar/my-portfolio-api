package org.portfolio.project.controller;

import org.portfolio.project.dto.ProjectDetailsDto;
import org.portfolio.project.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ProjectDetailsDto>> fetchProjects(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(projectService.getProjects(username));
    }

    @PostMapping("/")
    public ResponseEntity<ProjectDetailsDto> addProject(@RequestBody ProjectDetailsDto projectDetailsDto, Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(projectService.addProject(username, projectDetailsDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDetailsDto> updateProject(@PathVariable Long id, @RequestBody ProjectDetailsDto projectDetailsDto, Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(projectService.updateProject(username, projectDetailsDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();
        projectService.deleteProject(username, id);
        return ResponseEntity.noContent().build();
    }
}
