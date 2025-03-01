package com.quiz.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.activity.InvalidActivityException;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Value("classpath:users.json")
    private Resource usersResource;
    private Map<String, User> userCache = new HashMap<>();
    
    @PostConstruct
    public void init() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        if (usersResource.exists()) {
            objectMapper.readValue(usersResource.getInputStream(), new TypeReference<List<User>>() {}).forEach((user) -> userCache.put(user.getUsername(), user));
        } else {
            userCache = new HashMap<>();
        }
    }

    @Override
    public User registerUser(User user) throws IOException {
        if(userCache.get(user.getUsername()) == null) {
            userCache.put(user.getUsername(), user);
            saveUsersToFile();
            System.out.println("backend users" + userCache);
            return user;
        }
        else{
            throw new InvalidActivityException("User with this username already present");
        }
    }

   @Override
    public User getUserByUsername(String username) {
        System.out.println("get user "+ userCache);
        User user = userCache.getOrDefault(username, null);
        if (user != null) {
            System.out.println("User found: " + user); // Add logging here
        }
        return user;
    }

    @Override
    public User updateUserScore(String username, User updatedUser) throws IOException {
        Optional<User> existingUser = Optional.of(userCache.get(username));
        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();
            userToUpdate.setCorrectAnswers(updatedUser.getCorrectAnswers());
            userToUpdate.setIncorrectAnswers(updatedUser.getIncorrectAnswers());
            saveUsersToFile();
            return userToUpdate;
        }
        return null;
    }

    private void saveUsersToFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = usersResource.getFile();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, userCache.values().toArray());
    }
}