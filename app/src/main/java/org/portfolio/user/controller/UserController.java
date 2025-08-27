package org.portfolio.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.portfolio.user.dto.UserResponseDto;
import org.portfolio.user.dto.WorkExperienceDto;
import org.portfolio.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<UserResponseDto> getUser(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(userService.getUser(username));
    }

    @PostMapping("/workExperience")
    public ResponseEntity<WorkExperienceDto> addWorkExp(@Valid @RequestBody WorkExperienceDto workExperienceDto, Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(userService.addWorkExperience(username, workExperienceDto));
    }
}
