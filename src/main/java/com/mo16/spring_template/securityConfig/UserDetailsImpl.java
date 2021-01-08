package com.mo16.spring_template.securityConfig;


import com.mo16.spring_template.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = new LinkedList<>();
//        for (ApplicationRole role: user.getRole()) {
//            authorities.add(new SimpleGrantedAuthority(role));
//        }
//        for (ApplicationRole role : user.getRole()) {
//            role.getGrantedAuthorities();
//        }

        return user.getRole().getGrantedAuthoritiesList();

//        List<GrantedAuthority> authorities = Arrays.stream(user.getRoles()).map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
