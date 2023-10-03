package com.rest;

import com.kafkaconsumer.OrderConsumer;
import com.model.Ordermail;
import com.repository.OrdermailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.config","com.controller","com.service","com.kafkaconsumer"})
@EnableMongoRepositories(basePackages ="com.repository")
public class EmailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
	}
}



