package com.mo16.spring_template.user;

import com.mo16.spring_template.ExceptionHandeling.UserNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUser() {
        return userService.findAll();
    }

    @GetMapping("/user/{UserId}")
    @PreAuthorize("hasRole('ADMIN')")
    public User getUserById(@PathVariable Long UserId) {
        return userService.findById(UserId)
                .orElseThrow(() -> new UserNotFoundException(String.format("User id ( %d ) Not Found", UserId)));
    }

    @PostMapping("/user")
    @PreAuthorize("hasRole('ADMIN')")
    public User saveUser(@RequestBody User user) {
        user.setId(null);
        return userService.save(user);
    }


    @PutMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public User updateUser(@RequestBody User user, @PathVariable Long userId) {
        user.setId(userId);
        return userService.save(user);
    }


    @DeleteMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUserById(@PathVariable Long userId) {
        userService.deleteById(userId);
    }

    @DeleteMapping("/user")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@RequestBody User user) {
        userService.delete(user);
    }
}
