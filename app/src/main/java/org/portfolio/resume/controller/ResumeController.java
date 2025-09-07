package org.portfolio.resume.controller;

import lombok.RequiredArgsConstructor;
import org.portfolio.project.dto.ProjectDetailsDto;
import org.portfolio.project.service.ProjectService;
import org.portfolio.skills.dto.SkillsDto;
import org.portfolio.skills.service.SkillService;
import org.portfolio.user.dto.UserResponseDto;
import org.portfolio.user.dto.WorkExperienceDto;
import org.portfolio.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/portfolio")
@RequiredArgsConstructor
public class ResumeController {
    private final UserService userService;

    private final ProjectService projectService;

    private final SkillService skillService;

    @GetMapping
    public ResponseEntity<UserResponseDto> getUserDetails(@RequestParam String username) {
        return ResponseEntity.ok(userService.getUser(username));
    }

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectDetailsDto>> getProjects(@RequestParam String username) {
        return ResponseEntity.ok(projectService.getProjects(username));
    }

    @GetMapping("/skills")
    public ResponseEntity<SkillsDto> getSkills(@RequestParam String username) {
        return ResponseEntity.ok(skillService.getSkills(username));
    }

    @GetMapping("/workExp")
    public ResponseEntity<WorkExperienceDto> getWorkExperience(@RequestParam String username) {
        return ResponseEntity.ok(userService.getWorkExperience(username));
    }
}
