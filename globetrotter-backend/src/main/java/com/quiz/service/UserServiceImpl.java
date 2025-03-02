package com.quiz.service;

import com.quiz.model.User;
import com.quiz.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import javax.activity.InvalidActivityException;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() throws IOException {
        logger.info("✅ UserService initialized successfully!");
    }

    @Override
    public User registerUser(User user) throws IOException {
        if(userRepository.findByUsername(user.getUsername()) == null) {
            userRepository.save(user);
            logger.info("User with {} username successfully registered", user.getUsername());
            return user;
        }
        else{
            throw new InvalidActivityException("User with this username already present");
        }
    }

   @Override
    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            logger.info("User found with username {}", user.getUsername());
        }
        return user;
    }

    @Override
    public User updateUserScore(String username, User updatedUser) throws IOException {
        Optional<User> existingUser = Optional.of(userRepository.findByUsername(username));
        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();
            userToUpdate.setCorrectAnswers(updatedUser.getCorrectAnswers());
            userToUpdate.setIncorrectAnswers(updatedUser.getIncorrectAnswers());
            userRepository.save(userToUpdate);
            logger.info("Socre has been updated for user {}", username);
            return userToUpdate;
        }
        return null;
    }
}