package com.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void testUser() {
        // Create a User object
        User user = new User("john_doe", "password123", "john@example.com", "1990-01-15", true);

        // Check if the getters return the expected values
        assertEquals("john_doe", user.getUserName());
        assertEquals("password123", user.getPassword());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("1990-01-15", user.getdOB());
        assertTrue(user.getNotificationsAllowed());

        // Change the values using setters
        user.setUserName("jane_doe");
        user.setPassword("newPassword456");
        user.setEmail("jane@example.com");
        user.setdOB("1985-12-30");
        user.setNotificationsAllowed(false);

        // Check if the setters updated the values correctly
        assertEquals("jane_doe", user.getUserName());
        assertEquals("newPassword456", user.getPassword());
        assertEquals("jane@example.com", user.getEmail());
        assertEquals("1985-12-30", user.getdOB());
        assertFalse(user.getNotificationsAllowed());

        // Check the toString() method
        assertEquals("User{userName='jane_doe', password='newPassword456', email='jane@example.com', dOB='1985-12-30', notifications=false}", user.toString());
    }
}