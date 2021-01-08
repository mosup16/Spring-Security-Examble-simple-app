package com.mo16.spring_template.user;

import com.mo16.spring_template.post.Post;
import com.mo16.spring_template.roles.ApplicationRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Builder
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private ApplicationRole role;

    public User() {
        if (role == null) role = ApplicationRole.BASIC_USER;
    }

    public ApplicationRole getRole() {
        return role;
    }
}
