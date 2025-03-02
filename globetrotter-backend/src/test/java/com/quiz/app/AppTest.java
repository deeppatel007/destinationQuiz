package com.quiz.app;

import com.quiz.model.User;
import com.quiz.repository.UserRepository;
import com.quiz.service.UserService;
import com.quiz.service.UserServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;

import javax.activity.InvalidActivityException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppTest {

    
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("testUser");
        user.setCorrectAnswers(5);
        user.setIncorrectAnswers(2);
    }

    @Test
    void testRegisterUser_Success() throws IOException {
        when(userRepository.findByUsername("testUser")).thenReturn(null);
        when(userRepository.save(user)).thenReturn(user);

        User registeredUser = userService.registerUser(user);

        assertNotNull(registeredUser);
        assertEquals("testUser", registeredUser.getUsername());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testRegisterUser_AlreadyExists() {
        when(userRepository.findByUsername("testUser")).thenReturn(user);

        Exception exception = assertThrows(Exception.class, () -> userService.registerUser(user));

        assertEquals("User with this username already present", exception.getMessage());
    }

    @Test
    void testGetUserByUsername() {
        when(userRepository.findByUsername("testUser")).thenReturn(user);

        User foundUser = userService.getUserByUsername("testUser");

        assertNotNull(foundUser);
        assertEquals("testUser", foundUser.getUsername());
    }

    @Test
    void testGetUserByUsername_NotFound() {
        when(userRepository.findByUsername("unknownUser")).thenReturn(null);

        User foundUser = userService.getUserByUsername("unknownUser");

        assertNull(foundUser);
    }

    @Test
    void testUpdateUserScore_Success() throws IOException {
        User updatedUser = new User();
        updatedUser.setCorrectAnswers(10);
        updatedUser.setIncorrectAnswers(3);

        when(userRepository.findByUsername("testUser")).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.updateUserScore("testUser", updatedUser);

        assertNotNull(result);
        assertEquals(10, result.getCorrectAnswers());
        assertEquals(3, result.getIncorrectAnswers());
    }
}