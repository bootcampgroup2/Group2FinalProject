package com.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication(scanBasePackages = {"com.filter","com.util","com.exception"})
public class Apigateway1Application {

	public static void main(String[] args) {
		SpringApplication.run(Apigateway1Application.class, args);
	}

}
