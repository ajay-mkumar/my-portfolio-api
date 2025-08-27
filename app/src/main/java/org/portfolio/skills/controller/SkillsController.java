package org.portfolio.skills.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.portfolio.skills.dto.SkillsDto;
import org.portfolio.skills.service.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skills")
@RequiredArgsConstructor
public class SkillsController {
    private final SkillService skillService;

    @PostMapping("/")
    public ResponseEntity<SkillsDto> addSkills(@Valid @RequestBody SkillsDto skillSets, Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(skillService.addSkills(skillSets, username));
    }
}
