package org.portfolio.skills.service;

import org.portfolio.skills.dto.SkillsDto;
import org.springframework.stereotype.Service;

@Service
public interface SkillService {
    public SkillsDto addSkills(SkillsDto skillSets, String username);

    public SkillsDto getSkills(String username);
}
