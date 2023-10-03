package com.casestudy.orderservice.controller;

import com.controller.OrderController;
import com.kafkaproducer.OrderProducer;
import com.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderProducer orderProducer;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void testPlaceOrder() throws Exception {
        // Create a sample Order
        Order sampleOrder = new Order();
        sampleOrder.setOrderId(UUID.randomUUID().toString());
        // Mock the behavior of the orderProducer
        doNothing().when(orderProducer).sendMessage(any());

        // Create a request for the /orders/placeorder endpoint
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/orders/placeorder")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"orderProperty\":\"value\"}") // Replace with your actual JSON payload
                .header("User", "test@example.com"); // Replace with a valid email

        // Perform the request and expect a 200 OK response
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the orderProducer's sendMessage method was called
        verify(orderProducer, times(1)).sendMessage(any());
    }
}