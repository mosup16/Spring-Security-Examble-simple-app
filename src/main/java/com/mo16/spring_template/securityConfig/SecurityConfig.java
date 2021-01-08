package com.mo16.spring_template.securityConfig;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mo16.spring_template.roles.ApplicationPermission;
import com.mo16.spring_template.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.mo16.spring_template.roles.ApplicationRole.*;
import static org.springframework.http.HttpMethod.*;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
//        MessageDigest md = MessageDigest.getInstance("SHA-256");
//        md.update("hello".getBytes());
//        byte[] digest = md.digest();
//        System.out.println(new String(digest));
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String hello = encoder.encode("hello");
//        System.out.println(encoder.matches("hello", hello));
//
//    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserService userService, PasswordEncoder encoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder);
        provider.setUserDetailsService(new UserDetailsServiceImpl(userService));
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("mo")
//                .password("hello")
//                .roles("USER", "ADMIN")
//                .build();
//        List<UserDetails> users = new ArrayList<>();
//        users.add(user);
//        return new InMemoryUserDetailsManager(users);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/", "/css/*", "/js/*")
//                .hasAnyAuthority(BASIC_USER.getAuthoritiesArray())
//                .and()
//                .authorizeRequests()
//                .antMatchers("/user/**")
//                .hasAnyAuthority(ApplicationPermission.ROLE_ADMIN.name())
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic()
//        ;
        http.authorizeRequests()
                .antMatchers(GET ,"/user/**")
                .hasRole(ADMIN.name())
                .antMatchers(DELETE,"/user/**").hasRole(ADMIN.name())
                .antMatchers(PUT,"/user/**").hasRole(ADMIN.name())
                .antMatchers(POST, "/user/**").permitAll()
                .and().httpBasic()
        ;
//        http.csrf().disable().formLogin().defaultSuccessUrl("/user/current" ,true)
//                .and().rememberMe().key("remember-me").alwaysRemember(false);
//        ;
//        JWT.create().withSubject("").sign(Algorithm.HMAC256("secret"));

    }
}
