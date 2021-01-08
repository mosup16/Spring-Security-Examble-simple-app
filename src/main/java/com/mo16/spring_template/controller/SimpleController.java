package com.mo16.spring_template.controller;

import com.mo16.spring_template.roles.ApplicationRole;
import com.mo16.spring_template.user.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class SimpleController {

    private final UserService userService;

    public SimpleController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model) {
        System.out.println("hellllllllllo");
        ApplicationRole applicationRole = userService.findAll().get(0).getRole();

        System.out.println(applicationRole.name());
        model.addAttribute("usersList" ,userService.findAll());
        return "home/home.html";
    }

    @GetMapping("/user/current")
    @PreAuthorize("hasAnyAuthority('BASIC_AUTHORITY')")
    @ResponseBody
    public Principal user(Principal user){
        return user;
    }
}
