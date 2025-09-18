package org.portfolio.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.portfolio.user.dto.LoginRequestDto;
import org.portfolio.user.dto.LoginResponseDto;
import org.portfolio.user.dto.UserRequestDto;
import org.portfolio.user.dto.UserResponseDto;
import org.portfolio.user.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<LoginResponseDto> authUser(@Valid @RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(userService.loginUser(request.getUsername(), request.getPassword()));
    }

    @PostMapping(value = "/createUser", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserResponseDto> addUser(@Valid @RequestPart(value = "user") UserRequestDto userDto, @RequestPart(value = "profile-picture") MultipartFile profilePicture, @RequestPart(value = "resume") MultipartFile resume) {
        return ResponseEntity.ok(userService.createUser(userDto, profilePicture, resume));
    }
}
