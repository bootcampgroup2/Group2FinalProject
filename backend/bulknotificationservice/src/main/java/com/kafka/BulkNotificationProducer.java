package com.kafka;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Event;
import com.model.User;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class BulkNotificationProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(BulkNotificationProducer.class);
    private final KafkaTemplate<String, Event> kafkaTemplate;
    private static final String TOPIC_NAME = "bulk_notification";

    public BulkNotificationProducer(KafkaTemplate<String, Event> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendBulkNotificationMessage(List<User> users) {

        Event event = new Event();
        event.setMessage("Great Diwali Sale, Upto 50% off on each item, Sale is available from 20th october to 25th october");
        Message<Event> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, TOPIC_NAME)
                .build();
        System.out.println(message);
        
        Iterator<User> it = users.listIterator();
        while(it.hasNext()) {
        	event.setEmail(it.next().getEmail());
            kafkaTemplate.send(message);
        }
    }
}