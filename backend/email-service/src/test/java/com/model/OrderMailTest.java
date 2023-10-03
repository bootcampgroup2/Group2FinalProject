package com.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class OrderMailTest {

    @Test
    public void testOrdermail() {
        // Create an Ordermail object
        Ordermail ordermail = new Ordermail("test@example.com", null, false, "high");

        // Check if the getters return the expected values
        assertEquals("test@example.com", ordermail.getEmail());
        assertNull(ordermail.getMessage());
        assertFalse(ordermail.getRead());
        assertEquals("high", ordermail.getPriority());

        // Change the values using setters
        ordermail.setEmail("new@example.com");
        ordermail.setMessage(null);
        ordermail.setRead(true);
        ordermail.setPriority("low");

        // Check if the setters updated the values correctly
        assertEquals("new@example.com", ordermail.getEmail());
        assertNull(ordermail.getMessage());
        assertTrue(ordermail.getRead());
        assertEquals("low", ordermail.getPriority());
    }
}