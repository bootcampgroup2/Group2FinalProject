package com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.BulkNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/bulknotification")
@CrossOrigin(origins="*")
public class BulkNotificationController {

	@Autowired
	BulkNotificationService bulkNotificationService;
	 public static final Logger LOGGER=LoggerFactory.getLogger(BulkNotificationController.class);
	@GetMapping("/sendbulk")
	public ResponseEntity<?> sendBulkNotification(){
		bulkNotificationService.sendBulk();
		LOGGER.info("Bulk notification has been send to all users related to sales");
		return new ResponseEntity<>("send",HttpStatus.OK);
	}
}
