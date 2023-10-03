package com.model;

import com.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void testUser() {
        // Create a User object
        User user = new User("john_doe", "password123", "john@example.com", "1990-01-01");

        // Test the getter methods
        assertEquals("john_doe", user.getUserName());
        assertEquals("password123", user.getPassword());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("1990-01-01", user.getdOB());

        // Test the setter methods
        user.setUserName("jane_doe");
        user.setPassword("newpassword456");
        user.setEmail("jane@example.com");
        user.setdOB("1995-02-15");

        assertEquals("jane_doe", user.getUserName());
        assertEquals("newpassword456", user.getPassword());
        assertEquals("jane@example.com", user.getEmail());
        assertEquals("1995-02-15", user.getdOB());

        // Test toString method
        String expectedToString = "User [userName=jane_doe, password=newpassword456, email=jane@example.com, dOB=1995-02-15]";
        assertEquals(expectedToString, user.toString());
    }
}