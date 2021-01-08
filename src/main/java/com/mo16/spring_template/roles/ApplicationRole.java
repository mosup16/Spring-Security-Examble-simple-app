package com.mo16.spring_template.roles;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

import static com.mo16.spring_template.roles.ApplicationPermission.*;


public enum ApplicationRole {
    BASIC_USER(ROLE_BASIC_USER, BASIC_AUTHORITY),
    USER(ROLE_USER, BASIC_AUTHORITY, READ),
    ADMIN(ROLE_ADMIN, BASIC_AUTHORITY, READ, WRITE);

    private List<GrantedAuthority> grantedAuthorities = new LinkedList<>();
    private String[] authorities;

    ApplicationRole(ApplicationPermission... permissions) {
        init(permissions);
    }

    private void init(ApplicationPermission... permissions) {
        authorities = new String[permissions.length];
        for (int i = 0; i < permissions.length; i++) {
            String authority = permissions[i].name();
            authorities[i] = authority;
            grantedAuthorities.add(new SimpleGrantedAuthority(authority));
        }
    }

//    private List<ApplicationPermission> getPermissions() {
//        return permissionsList;
//    }

    // for UserDetailsImpl
    public List<GrantedAuthority> getGrantedAuthoritiesList() {
//        return permissionsList.stream()
//                .map(applicationPermission -> new SimpleGrantedAuthority(applicationPermission.name()))
//                .collect(Collectors.toList());
        return grantedAuthorities;
    }

    // for security configuration
    public String[] getAuthoritiesArray() {
        return authorities;
    }
}
