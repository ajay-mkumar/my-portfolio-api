package org.portfolio.user.mapper;

import org.portfolio.user.dto.WorkExperienceDto;
import org.portfolio.user.modal.User;
import org.portfolio.user.modal.WorkExperience;

public class WorkExperienceMapper {
    public static WorkExperience toEntity(WorkExperienceDto dto, User user) {
        if (dto == null) return null;

        WorkExperience workExperience = new WorkExperience();
        workExperience.setUser(user);
        workExperience.setCompanyName(dto.getCompanyName());
        workExperience.setDesignation(dto.getDesignation());
        workExperience.setDuration(dto.getDuration());
        workExperience.setWorkDetails(dto.getWorkDetails());

        return workExperience;
    }

    public static WorkExperienceDto toDto(WorkExperience workExperience) {
        if (workExperience == null) return null;

        WorkExperienceDto dto = new WorkExperienceDto();
        dto.setId(workExperience.getId());
        dto.setCompanyName(workExperience.getCompanyName());
        dto.setDesignation(workExperience.getDesignation());
        dto.setDuration(workExperience.getDuration());
        dto.setWorkDetails(workExperience.getWorkDetails());

        return dto;
    }
}
