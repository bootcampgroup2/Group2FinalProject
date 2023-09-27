package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.BulkNotificationService;

@RestController
@RequestMapping("/bulknotification")
public class BulkNotificationController {

	@Autowired
	BulkNotificationService bulkNotificationService;
	
	@GetMapping("/sendbulk")
	public ResponseEntity<?> sendBulkNotification(){
		bulkNotificationService.sendBulk();
		
		return new ResponseEntity<>("send",HttpStatus.OK);
	}
}
