package org.portfolio.resume.controller;

import lombok.RequiredArgsConstructor;
import org.portfolio.user.dto.UserResponseDto;
import org.portfolio.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolio")
@RequiredArgsConstructor
public class ResumeController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserResponseDto> getUserDetails(@RequestParam String username) {
        return ResponseEntity.ok(userService.getUser(username));
    }
}
