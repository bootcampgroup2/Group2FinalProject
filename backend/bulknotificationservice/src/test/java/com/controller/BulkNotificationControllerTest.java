package com.controller;
import com.service.BulkNotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

public class BulkNotificationControllerTest {

    @InjectMocks
    private BulkNotificationController bulkNotificationController;

    @Mock
    private BulkNotificationService bulkNotificationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSendBulkNotification() {
        // Mock the behavior of bulkNotificationService
        doNothing().when(bulkNotificationService).sendBulk();

        // Call the method under test
        ResponseEntity<?> responseEntity = bulkNotificationController.sendBulkNotification();

        // Verify that bulkNotificationService.sendBulk() was called
        verify(bulkNotificationService, times(1)).sendBulk();

        // Verify the response status code is HttpStatus.OK (200)
        assert(responseEntity.getStatusCode() == HttpStatus.OK);
    }
}



