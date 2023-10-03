package com.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.exception.CustomException;
import com.model.User;
import com.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        // Create a list of mock User objects
        List<User> userList = new ArrayList<>();
        userList.add(new User("user1", "password1", "user1@example.com", "2000-01-01", true));
        userList.add(new User("user2", "password2", "user2@example.com", "1995-05-05", true));

        // Mock the behavior of userRepository.findAll()
        when(userRepository.findAll()).thenReturn(userList);

        // Call the method to be tested
        List<User> result = userService.getAllUsers();

        // Assertions
        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUserName());
        assertEquals("user2", result.get(1).getUserName());
    }

    @Test
    public void testGetUserByEmail() {
        // Create a mock User object
        User mockUser = new User("user1", "password1", "user1@example.com", "2000-01-01", true);

        // Mock the behavior of userRepository.findByEmail()
        when(userRepository.findByEmail("user1@example.com")).thenReturn(mockUser);

        // Call the method to be tested
        User result = userService.getUser("user1@example.com");

        // Assertions
        assertNotNull(result);
        assertEquals("user1@example.com", result.getEmail());
    }

    @Test
    public void testGetUserByEmailNotFound() {
        // Mock the behavior of userRepository.findByEmail() when the user is not found
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        // Call the method to be tested and expect an error to be thrown
        assertThrows(Error.class, () -> userService.getUser("nonexistent@example.com"));
    }

    @Test
    public void testCheckUserExists() {
        // Mock the behavior of userRepository.findByEmail() when the user exists
        when(userRepository.findByEmail("user1@example.com")).thenReturn(new User());

        // Call the method to be tested
        boolean result = userService.checkUser("user1@example.com");

        // Assertions
        assertFalse(result); // User exists, so it should return false
    }

    @Test
    public void testCheckUserDoesNotExist() {
        // Mock the behavior of userRepository.findByEmail() when the user does not exist
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        // Call the method to be tested
        boolean result = userService.checkUser("nonexistent@example.com");

        // Assertions
        assertTrue(result); // User does not exist, so it should return true
    }

    @Test
    public void testUpdateUserNotifications() {
        // Create a mock User object
        User mockUser = new User("user1", "password1", "user1@example.com", "2000-01-01", true);

        // Mock the behavior of userRepository.findByEmail() and userRepository.save()
        when(userRepository.findByEmail("user1@example.com")).thenReturn(mockUser);

        // Call the method to be tested
        userService.updateUser("user1@example.com");

        // Assertions
       // assertTrue(mockUser.getNotificationsAllowed()); // Notifications should be toggled to true
        verify(userRepository, times(1)).deleteByEmail("user1@example.com"); // Verify that userRepository.deleteByEmail() was called once
        verify(userRepository, times(1)).save(mockUser); // Verify that userRepository.save() was called once
    }

    @Test
    public void testAddUser() {
        // Create a mock User object
        User mockUser = new User("user1", "password1", "user1@example.com", "2000-01-01", true);

        // Mock the behavior of userRepository.findByEmail() when the user does not exist
        when(userRepository.findByEmail("user1@example.com")).thenReturn(null);

        // Mock the behavior of passwordEncoder.encode()
        when(passwordEncoder.encode("password1")).thenReturn("encodedPassword1");

        // Mock the behavior of userRepository.save()
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        // Call the method to be tested
        assertDoesNotThrow(() -> userService.addUser(mockUser));

        // Assertions
        assertTrue(mockUser.getNotificationsAllowed()); // Notifications should be set to true
        assertEquals("encodedPassword1", mockUser.getPassword()); // Password should be encoded
        verify(userRepository, times(1)).save(mockUser); // Verify that userRepository.save() was called once
    }

    @Test
    public void testAddUserWithEmailExists() {
        // Create a mock User object
        User mockUser = new User("user1", "password1", "user1@example.com", "2000-01-01", true);

        // Mock the behavior of userRepository.findByEmail() when the user already exists
        when(userRepository.findByEmail("user1@example.com")).thenReturn(mockUser);

        // Call the method to be tested and expect a CustomException to be thrown
        assertThrows(CustomException.class, () -> userService.addUser(mockUser));
    }

    @Test
    public void testDeleteUser() {
        // Mock the behavior of userRepository.deleteByEmail()
        //doNothing().when(userRepository).deleteByEmail("user1@example.com");

        // Call the method to be tested
        userService.deleteUser("user1@example.com");

        // Verify that userRepository.deleteByEmail() was called once
        verify(userRepository, times(1)).deleteByEmail("user1@example.com");
        verifyNoMoreInteractions(userRepository); // Ensure that there are no more interactions with userRepository
    }
}