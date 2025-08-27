package org.portfolio.user.repository;

import org.portfolio.user.modal.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<WorkExperience, Long> {
}
