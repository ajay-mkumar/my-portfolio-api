package org.portfolio.user.repository;

import org.portfolio.user.modal.User;
import org.portfolio.user.modal.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkRepository extends JpaRepository<WorkExperience, Long> {
    List<WorkExperience> findByUser(User user);
}
