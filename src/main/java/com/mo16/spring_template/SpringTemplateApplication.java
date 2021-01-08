package com.mo16.spring_template;

import com.mo16.spring_template.post.Post;
import com.mo16.spring_template.post.PostService;
import com.mo16.spring_template.user.User;
import com.mo16.spring_template.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

import static com.mo16.spring_template.roles.ApplicationRole.ADMIN;
import static com.mo16.spring_template.roles.ApplicationRole.USER;


@SpringBootApplication
public class SpringTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTemplateApplication.class, args);
    }

    @Bean
    public CommandLineRunner cmd(UserService userService, PostService postService){
        return args -> {
            User mo = User.builder().name("mo").password("hello")
                    .role(ADMIN)
                    .build();
            User emad = User.builder().name("emad").password("welcome")
                    .role(USER)
                    .build();
            User savedMo = userService.save(mo);
            User savedEmad = userService.save(emad);
            Arrays.asList(
                    Post.builder().user(savedMo).content("hello world 1").build(),
                    Post.builder().user(savedMo).content("hello world 2").build(),
                    Post.builder().user(savedMo).content("hello world 3").build()
            ).forEach(postService::save);
            Arrays.asList(
                    Post.builder().user(savedEmad).content("hello world again 1").build(),
                    Post.builder().user(savedEmad).content("hello world again 2").build(),
                    Post.builder().user(savedEmad).content("hello world again 3").build()
            ).forEach(postService::save);

        };
    }

}
