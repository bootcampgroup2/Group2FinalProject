package com.controller;



import com.config.MyUserDetailsService;
import com.model.LoginRequest;
import com.service.JwtUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class HomeControllerTest {

    @InjectMocks
    private HomeController homeController;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtility jwtUtility;

    @Mock
    private MyUserDetailsService myUserDetailsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoginSuccess() throws Exception {
        // Mock the behavior of authenticationManager
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
            .thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(true);

        // Mock the behavior of jwtUtility
        when(jwtUtility.generateToken(anyString())).thenReturn("mocked-token");

        // Create a sample LoginRequest
        LoginRequest loginRequest = new LoginRequest("test@example.com", "password");
        //loginRequest.setEmail("test@example.com");
        //loginRequest.setPassword("password");

        // Call the method under test
        ResponseEntity<?> responseEntity = homeController.login(loginRequest);

        // Verify the response contains a token and has a status of HttpStatus.OK
        assertEquals("mocked-token", responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testLoginFailure() throws Exception {
        // Mock the behavior of authenticationManager to throw BadCredentialsException
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
            .thenThrow(new BadCredentialsException("Invalid credentials"));

        // Create a sample LoginRequest
        LoginRequest loginRequest = new LoginRequest("test@example.com", "password");
        //loginRequest.setEmail("test@example.com");
        //loginRequest.setPassword("incorrect-password");

        // Call the method under test
        ResponseEntity<?> responseEntity = homeController.login(loginRequest);

        // Verify the response contains an error message and has a status of HttpStatus.FORBIDDEN
        assertEquals("Invalid credentials", responseEntity.getBody());
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }
}
