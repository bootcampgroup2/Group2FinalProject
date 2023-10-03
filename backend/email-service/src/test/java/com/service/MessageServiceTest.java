package com.service;

import com.model.Ordermail;
import com.repository.OrdermailRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class MessageServiceTest {

    @Mock
    private OrdermailRepository orderMailRepository;

    @InjectMocks
    private MessageService messageService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateToRead() {
        // Create a sample Ordermail object
        Ordermail sampleOrderMail = new Ordermail("test@example.com", null, false, "high");
        sampleOrderMail.setId("1"); // Set a sample ID

        // Mock the behavior of orderMailRepository.findById()
        when(orderMailRepository.findById("1")).thenReturn(Optional.of(sampleOrderMail));

        // Call the updateToRead method
        messageService.updateToRead("1");

        // Verify that setRead(true) was called and the object was saved
        assertTrue(sampleOrderMail.getRead());
        verify(orderMailRepository, times(1)).save(sampleOrderMail);
    }
}