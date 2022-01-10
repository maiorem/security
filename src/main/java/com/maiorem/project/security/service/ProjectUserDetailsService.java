package com.maiorem.project.security.service;

import com.maiorem.project.entity.ProjectMember;
import com.maiorem.project.repository.ProjectMemberRepository;
import com.maiorem.project.security.dto.ProjectAuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProjectUserDetailsService implements UserDetailsService {

    private final ProjectMemberRepository projectMemberRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("ProjectUserDetailsService loadUserByUsername " + username);

        Optional<ProjectMember> result = projectMemberRepository.findByEmail(username, false);

        if(result.isPresent()) {
            throw new UsernameNotFoundException("Check Email or Social ");
        }

        ProjectMember projectMember = result.get();

        log.info("------------------------------------------");
        log.info(projectMember);

        ProjectAuthMemberDTO projectAuthMember = new ProjectAuthMemberDTO(
                projectMember.getEmail(),
                projectMember.getPassword(),
                projectMember.isFromSocial(),
                projectMember.getRoleSet().stream()
                        .map(role -> new SimpleGrantedAuthority("Role_"+role.name())).collect(Collectors.toSet())
        );

        projectAuthMember.setName(projectMember.getName());
        projectAuthMember.setFromSocial(projectMember.isFromSocial());

        return projectAuthMember;
    }
}
