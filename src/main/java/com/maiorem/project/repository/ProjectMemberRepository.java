package com.maiorem.project.repository;

import com.maiorem.project.entity.ProjectMember;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, String> {

    @EntityGraph(attributePaths = { "roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from ProjectMember m where m.fromSocial = :social and m.email =:email")
    Optional<ProjectMember> findByEmail(String email, boolean social);

}
