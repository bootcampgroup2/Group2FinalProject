package com.service;


import com.service.BulkNotificationService;
import com.kafka.BulkNotificationProducer;
import com.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class BulkNotificationServiceTest {

    private BulkNotificationService bulkNotificationService;
    private RestTemplate restTemplate;
    private BulkNotificationProducer bulkNotificationProducer;

    @BeforeEach
    public void setUp() {
        restTemplate = Mockito.mock(RestTemplate.class);
        bulkNotificationProducer = Mockito.mock(BulkNotificationProducer.class);
        bulkNotificationService = new BulkNotificationService();
    }

    @Test
    public void testSendBulk() {
        // Create a list of mock users
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(new User("john_doe", "password123", "john@example.com", "1990-01-01",true));
        mockUsers.add(new User("john", "password123", "john123@example.com", "1990-09-08",true));

        // Mock the ResponseEntity that would be returned by the REST call
        ResponseEntity<List<User>> responseEntity = ResponseEntity.ok(mockUsers);

        // Mock the REST call using RestTemplate
        when(restTemplate.exchange(
                eq("http://localhost:8082/user/getusers"),
                eq(HttpMethod.GET),
                eq(null),
                eq(new ParameterizedTypeReference<List<User>>() {})
        )).thenReturn(responseEntity);

        // Call the sendBulk method
        //bulkNotificationService.sendBulk();

        // Verify that the BulkNotificationProducer's sendBulkNotificationMessage was called once
        //verify(bulkNotificationProducer, times(1)).sendBulkNotificationMessage(eq(mockUsers));
    }
}
