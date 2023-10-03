package com.model;




import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testEvent() {
        // Create an Event object
        Event event = new Event("test@example.com", "Hello, world!", false);

        // Check if the getters return the expected values
        assertEquals("test@example.com", event.getEmail());
        assertEquals("Hello, world!", event.getMessage());
        assertFalse(event.isRead());

        // Change the values using setters
        event.setEmail("new@example.com");
        event.setMessage("Updated message");
        event.setRead(true);

        // Check if the setters updated the values correctly
        assertEquals("new@example.com", event.getEmail());
        assertEquals("Updated message", event.getMessage());
        assertTrue(event.isRead());

        // Check the toString() method
        assertEquals("Event [email=new@example.com, message=Updated message, read=true]", event.toString());
    }
}