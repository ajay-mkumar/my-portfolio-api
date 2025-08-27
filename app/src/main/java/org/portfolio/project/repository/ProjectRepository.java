package org.portfolio.project.repository;

import org.portfolio.project.modal.Project;
import org.portfolio.user.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByUser(User user);
}
