package org.portfolio.skills.service;

import lombok.RequiredArgsConstructor;
import org.portfolio.skills.dto.SkillsDto;
import org.portfolio.skills.mapper.SkillsMapper;
import org.portfolio.skills.modal.Skills;
import org.portfolio.user.modal.User;
import org.portfolio.skills.repository.SkillsRepository;
import org.portfolio.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService{
    private final SkillsRepository skillsRepository;
    private final UserRepository userRepository;

    public SkillsDto addSkills(SkillsDto skillSets, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Optional<Skills> previouslySavedSkills = skillsRepository.findByUser(user);
        Skills skills = previouslySavedSkills.orElseGet(() -> SkillsMapper.toEntity(skillSets, user));
        skills.setSkillSets(skillSets.getSkillSets());
        return SkillsMapper.toDto(skillsRepository.save(skills));
    }
}
