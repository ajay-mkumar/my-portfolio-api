package org.portfolio.skills.mapper;

import org.portfolio.skills.dto.SkillsDto;
import org.portfolio.skills.modal.Skills;
import org.portfolio.user.modal.User;

public class SkillsMapper {
    public static Skills toEntity(SkillsDto dto, User user) {
        if (dto == null) return null;

        Skills skills = new Skills();
        skills.setUser(user);
        skills.setSkillSets(dto.getSkillSets());

        return skills;
    }

    public static SkillsDto toDto(Skills skills) {
        if (skills == null) return null;

        SkillsDto dto = new SkillsDto();
        dto.setSkillSets(skills.getSkillSets());
        return dto;
    }
}
