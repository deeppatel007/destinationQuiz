// UserService.java
package com.quiz.service;

import com.quiz.model.User;
import java.io.IOException;

public interface UserService {
    User registerUser(User user) throws IOException;
    User getUserByUsername(String username);
    User updateUserScore(String username, User updatedUser) throws IOException;
}