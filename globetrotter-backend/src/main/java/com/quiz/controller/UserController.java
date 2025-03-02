package com.quiz.controller;

import com.quiz.model.User;
import com.quiz.service.UserService;
import com.quiz.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins ={"http://localhost:3000","https://destinationquiznew.onrender.com/", "https://destinationquiznew.onrender.com"}) 
public class UserController {

    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) throws IOException {
        logger.info("Recived request to register user with username {}", user.getUsername());
        return userService.registerUser(user);
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        logger.info("Recived request to get user with username {}", username);
        return userService.getUserByUsername(username);
    }

    @PutMapping("/{username}/score")
    public User updateUserScore(@PathVariable String username, @RequestBody User user) throws IOException {
        logger.info("Recived request to update userScore for username {}", username);
        return userService.updateUserScore(username, user);
    }
}