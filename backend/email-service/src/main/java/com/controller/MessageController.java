package com.controller;

import com.model.Ordermail;
import com.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/email")
public class MessageController {
    @Autowired
    private MessageService messageService;
    public static final Logger LOGGER=LoggerFactory.getLogger(MessageController.class);
    @GetMapping("/messages")
    public List<Ordermail> getUnreadMessages(@RequestHeader("User") String email) {
    	System.out.println(email);
    	LOGGER.info("Order place......notification is been sent to user"+email);
        return messageService.getUnreadMessagesByEmail(email);
    }
    
    @PutMapping("/updatetoread/{id}")
    public ResponseEntity<?> updateToRead(@PathVariable String id){
    	messageService.updateToRead(id);
    	LOGGER.info("The user has viewed the notification(seen)");
    	return new ResponseEntity<>("Updated",HttpStatus.ACCEPTED);
    }
}