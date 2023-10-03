package com.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

import com.kafka.BulkNotificationProducer;
import com.model.User;

@Service
public class BulkNotificationService {

	ArrayList<User> users = new ArrayList<>();
	
	@Autowired
	BulkNotificationProducer bulkNotificationProducer;
	@Autowired
	RestTemplate restTemplate;
	
	public void sendBulk() {
		String url = "http://localhost:8082/user/getusers";
		
		ResponseEntity<List<User>> response = restTemplate.exchange(url, HttpMethod.GET, null,new ParameterizedTypeReference<List<User>>(){});
		
		System.out.println(response.getBody());
		
		bulkNotificationProducer.sendBulkNotificationMessage(response.getBody());
	}
}
