package com.quiz.controller;

import com.quiz.model.User;
import com.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.io.IOException;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000") 
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) throws IOException {
        System.out.println("user controller"+ user.getUsername());
        return userService.registerUser(user);
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        System.out.println("get usr neame controller"+ username);
        return userService.getUserByUsername(username);
    }

    @PutMapping("/{username}/score")
    public User updateUserScore(@PathVariable String username, @RequestBody User user) throws IOException {
        return userService.updateUserScore(username, user);
    }
}