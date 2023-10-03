package com.controller;

import com.kafkaproducer.OrderProducer;
import com.model.Order;
import com.model.OrderEvent;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }
    @PostMapping("/placeorder")
    public String placeOrder(@RequestBody Order order,@RequestHeader("User") String email){
        order.setOrderId((UUID.randomUUID().toString()));
        
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("successfull");
        orderEvent.setMessage("Your order will be delivered soon");
        orderEvent.setOrder(order);
        orderEvent.setEmail(email);
        orderEvent.setIsInstantEmail(true);
        
        orderProducer.sendMessage(orderEvent);

        return "Order Placed successfully";

    }
}


