package com.maiorem.project.entity;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import java.util.HashSet;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ProjectMember {

    @Id
    private String email;

    private String password;

    private String name;

    private boolean fromSocial;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<ProjectMemberRole> roleSet = new HashSet<>();

    public void addMemberRole(ProjectMemberRole projectMemberRole) {
        roleSet.add(ProjectMemberRole);
    }


}
