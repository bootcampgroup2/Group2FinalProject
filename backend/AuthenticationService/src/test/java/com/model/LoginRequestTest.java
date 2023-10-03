package com.model;

import com.model.LoginRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginRequestTest {

    @Test
    public void testLoginRequest() {
        // Create a LoginRequest object
        LoginRequest loginRequest = new LoginRequest("user@example.com", "password123");

        // Test the getter methods
        assertEquals("user@example.com", loginRequest.getEmail());
        assertEquals("password123", loginRequest.getPassword());

        // Test the setter methods
        loginRequest.setEmail("newuser@example.com");
        loginRequest.setPassword("newpassword456");

        assertEquals("newuser@example.com", loginRequest.getEmail());
        assertEquals("newpassword456", loginRequest.getPassword());

        // Test toString method
        String expectedToString = "LoginRequest [email=newuser@example.com, password=newpassword456]";
        assertEquals(expectedToString, loginRequest.toString());
    }
}