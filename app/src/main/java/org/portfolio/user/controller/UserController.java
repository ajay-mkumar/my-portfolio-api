package org.portfolio.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.portfolio.user.dto.UserResponseDto;
import org.portfolio.user.dto.UserUpdateDto;
import org.portfolio.user.dto.WorkExperienceDto;
import org.portfolio.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PutMapping("/workExperience")
    public ResponseEntity<WorkExperienceDto> addWorkExp(@Valid @RequestBody WorkExperienceDto workExperienceDto, @RequestParam(required = false) Long id, Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(userService.addOrUpdateWorkExperience(username, workExperienceDto, id));
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserResponseDto> updateUser(@Valid @RequestPart(value = "user") UserUpdateDto userDto, @RequestPart(value = "profile-picture") MultipartFile profilePicture, @RequestPart(value = "resume") MultipartFile resume, Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(userService.updateUser(username, userDto, profilePicture, resume));
    }

    @DeleteMapping("/workExperience/{id}")
    public ResponseEntity<String> deleteWorkExp(@PathVariable Long id, Authentication authentication) {
        userService.deleteWorkExperience(authentication.getName(), id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
