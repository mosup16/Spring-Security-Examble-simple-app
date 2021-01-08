package com.mo16.spring_template.securityConfig;

import com.mo16.spring_template.user.User;
import com.mo16.spring_template.user.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("user name is  invalid"));
        return new UserDetailsImpl(user);
    }
}
