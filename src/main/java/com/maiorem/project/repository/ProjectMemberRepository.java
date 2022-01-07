package com.maiorem.project.repository;

import com.maiorem.project.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, String> {
}
