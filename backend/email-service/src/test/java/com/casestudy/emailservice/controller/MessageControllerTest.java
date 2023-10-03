package com.casestudy.emailservice.controller;


import com.controller.MessageController;
import com.model.Ordermail;
import com.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MessageControllerTest {

    @Mock
    private MessageService messageService;

    @InjectMocks
    private MessageController messageController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUnreadMessages() {
        // Arrange
        String userEmail = "test@example.com";
        Ordermail message1 = new Ordermail();
        Ordermail message2 = new Ordermail();
        List<Ordermail> messages = Arrays.asList(message1, message2);

        when(messageService.getUnreadMessagesByEmail(userEmail)).thenReturn(messages);

        // Act
        List<Ordermail> result = messageController.getUnreadMessages(userEmail);

        // Assert
        assertEquals(2, result.size());
        assertEquals(message1, result.get(0));
        assertEquals(message2, result.get(1));
    }

    @Test
    public void testUpdateToRead() {
        // Arrange
        String messageId = "123";
        ResponseEntity<?> expectedResponse = new ResponseEntity<>("Updated", HttpStatus.ACCEPTED);

        // Act
        ResponseEntity<?> result = messageController.updateToRead(messageId);

        // Assert
        assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
        assertEquals("Updated", result.getBody());
        verify(messageService, times(1)).updateToRead(messageId);
    }
}