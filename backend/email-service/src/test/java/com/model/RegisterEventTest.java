package com.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RegisterEventTest {

    @Test
    public void testRegisterEvent() {
        // Create a RegisterEvent object
        RegisterEvent registerEvent = new RegisterEvent("test@example.com", "Event registration", "2023-09-26 10:00 AM");

        // Check if the getters return the expected values
        assertEquals("test@example.com", registerEvent.getEmail());
        assertEquals("Event registration", registerEvent.getMessage());
        assertEquals("2023-09-26 10:00 AM", registerEvent.getDateAndTime());

        // Change the values using setters
        registerEvent.setEmail("new@example.com");
        registerEvent.setMessage("Updated message");
        registerEvent.setDateAndTime("2023-09-27 2:30 PM");

        // Check if the setters updated the values correctly
        assertEquals("new@example.com", registerEvent.getEmail());
        assertEquals("Updated message", registerEvent.getMessage());
        assertEquals("2023-09-27 2:30 PM", registerEvent.getDateAndTime());

        // Check the toString() method
        assertEquals("RegisterEvent{email='new@example.com', message='Updated message'}", registerEvent.toString());
    }
}