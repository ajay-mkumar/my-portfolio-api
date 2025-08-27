package org.portfolio.skills.repository;

import org.portfolio.skills.modal.Skills;
import org.portfolio.user.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillsRepository extends JpaRepository<Skills, Long> {
    public Optional<Skills> findByUser(User user);
}
