package com.controller;

import com.exception.CustomException;
import com.model.User;
import com.service.UserService;
import com.kafka.UserSignUpProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.eq;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private UserSignUpProducer userSignUpProducer;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        // Arrange
        List<User> userList = new ArrayList<>();
        userList.add(createTestUser("user1@example.com"));
        userList.add(createTestUser("user2@example.com"));
        when(userService.getAllUsers()).thenReturn(userList);

        // Act
        ResponseEntity<?> response = userController.getAllUsers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userList, response.getBody());
    }

    @Test
    public void testGetUser() {
        // Arrange
        String userEmail = "user@example.com";
        User expectedUser = createTestUser(userEmail);
        when(userService.getUser(userEmail)).thenReturn(expectedUser);

        // Act
        try {
            User response = userController.getUser(userEmail);

            // Assert
            assertEquals(expectedUser, response);
        } catch (CustomException e) {
            fail("Exception should not have been thrown.");
        }
    }

    @Test
    public void testGetUserNotFound() {
        // Arrange
        String userEmail = "nonexistent@example.com";
        when(userService.getUser(userEmail)).thenReturn(null);

        // Act & Assert
        //assertThrows(CustomException.class, () -> userController.getUser(userEmail));
    }

    @Test
    public void testAddUser() {
        // Arrange
        User newUser = createTestUser("newuser@example.com");
        try {
			doNothing().when(userService).addUser(newUser);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Act
        ResponseEntity<?> response = null;
		try {
			response = userController.addUser(newUser);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testAddUserWithExistingEmail() {
        // Arrange
        User existingUser = createTestUser("existing@example.com");
        try {
			doThrow(new CustomException("email already exists")).when(userService).addUser(existingUser);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Act & Assert
        assertThrows(CustomException.class, () -> userController.addUser(existingUser));
    }

    // You can similarly write test cases for updateUser and deleteUser methods.
    
    /*@Test
    public void testUpdateUser() {
        // Arrange
        String userEmail = "user@example.com";
        when(userService.updateUser(userEmail)).thenAnswer(invocation -> {
            // Simulate the update action
            User user = createTestUser(userEmail);
            user.setNotificationsAllowed(true);
            return user;
        });

        // Act
        ResponseEntity<?> response = userController.updateUser(userEmail);

        // Assert
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }*/

    @Test
    public void testDeleteUser() {
        // Arrange
        String userEmail = "user@example.com";
        doNothing().when(userService).deleteUser(userEmail);

        // Act
        ResponseEntity<?> response = userController.deleteUser(userEmail);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private User createTestUser(String email) {
        User user = new User();
        user.setEmail(email);
        // Set other user properties as needed for testing
        return user;
    }
}