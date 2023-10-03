package com.service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import com.service.JwtUtility;
public class JwtUtilityTest {

    private JwtUtility jwtUtility;

    @BeforeEach
    public void setUp() {
        jwtUtility = new JwtUtility();
    }

    @Test
    public void testGenerateToken() {
        String userName = "testUser";
        String token = jwtUtility.generateToken(userName);
        assertNotNull(token);
    }

    @Test
    public void testValidateValidToken() {
        String userName = "testUser";
        String token = jwtUtility.generateToken(userName);

        assertDoesNotThrow(() -> jwtUtility.validateToken(token));
    }

    /*@Test
    public void testValidateExpiredToken() {
        // Create an expired token (1 millisecond expiration)
        String userName = "testUser";
        String expiredToken = jwtUtility.createToken(new HashMap<>(), userName);

        assertThrows(ExpiredJwtException.class, () -> jwtUtility.validateToken(expiredToken));
    }*/

    @Test
    public void testValidateInvalidToken() {
        // Create a valid token
        String userName = "testUser";
        String validToken = jwtUtility.generateToken(userName);

        // Alter the token (e.g., remove a character from the middle)
        String alteredToken = validToken.substring(0, validToken.length() / 2) + validToken.substring(validToken.length() / 2 + 1);

        // Expect a SignatureException when validating the altered token
        assertThrows(SignatureException.class, () -> jwtUtility.validateToken(alteredToken));
    }
}