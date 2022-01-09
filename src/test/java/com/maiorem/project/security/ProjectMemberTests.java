package com.maiorem.project.security;

import com.maiorem.project.entity.ProjectMember;
import com.maiorem.project.entity.ProjectMemberRole;
import com.maiorem.project.repository.ProjectMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ProjectMemberTests {

    @Autowired
    private ProjectMemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies() {

        // 1 ~ 100 중에 81 ~ 90이면 MANAGER 91 ~ 100 이면 ADMIN 나머지 USER
        IntStream.rangeClosed(1, 100).forEach(i -> {
            ProjectMember projectMember = ProjectMember.builder()
                    .email("user"+i+"@maiorem.com")
                    .name("유저"+i)
                    .fromSocial(false)
                    .password(passwordEncoder.encode("1111"))
                    .build();

            projectMember.addMemberRole(ProjectMemberRole.USER);

            if(i>80) {
                projectMember.addMemberRole(ProjectMemberRole.MANAGER);
            }

            if(i>90) {
                projectMember.addMemberRole(ProjectMemberRole.ADMIN);
            }

            repository.save(projectMember);

        });

    }

    @Test
    public void testRead() {

        Optional<ProjectMember> result = repository.findByEmail("user95@maiorem.com", false);

        ProjectMember projectMember = result.get();

        System.out.println(projectMember);


    }

}

