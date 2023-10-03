

    
package com.kafkaconsumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.model.Event;
import com.model.OrderEvent;
import com.model.Ordermail;
import com.model.RegisterEvent;
import com.model.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.repository.OrdermailRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private Configuration config;

    @Autowired 
    RestTemplate restTemplate;
    @Autowired
    private OrdermailRepository ordermailRepository;

    @KafkaListener(topics = "${spring.kafka.topic.name}",groupId = "${spring.kafka.consumer.group-id}")
    public  void  consume(OrderEvent event) throws MessagingException{
    	
    	String url = "http://localhost:8082/user/getuser/"+event.getEmail();
    	String email = event.getEmail();
    	
    	System.out.println("Before");
    	ResponseEntity<User> res = restTemplate.exchange(url, HttpMethod.GET, null,new ParameterizedTypeReference<User>(){});
    	
    	System.out.println(res.getBody());
    	LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
    	
        
        Map<String,Object> model = new HashMap<>();
    	model.put("message",event.getMessage());
    	model.put("status", event.getStatus());
    	model.put("event", "Order");
    	model.put("orderId",event.getOrder().getOrderId());
    	model.put("itemName", event.getOrder().getName());
    	model.put("quantity",event.getOrder().getQty());
    	model.put("price",event.getOrder().getPrice());
    	model.put("email",event.getEmail());
    	model.put("dateAndTime", formattedDateTime);
    	model.put("from", "p87773623@gmail.com,e-Mail Notify");
    	Ordermail order = new Ordermail(event.getEmail(),model,false,"high");
    	
        ordermailRepository.save(order);
        
        if(res.getBody().getNotificationsAllowed() == true) {
        	if(event.getIsInstantEmail()){
                LOGGER.info("Order event recieved in email service => %models",event.toString());
                simpleEmail(event.getEmail(),"Order",model);
                System.out.println("Called simpleemail!!!");
            }else{
                System.out.println("Send email to user subscribed to an event");
            }
            LOGGER.info("Order event recieved in email service => %models",event.toString());
            try {
                // Sleep for 1 minute (60,000 milliseconds)
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                // Handle the InterruptedException
                // For example, you can log the interruption and decide how to proceed
                Thread.currentThread().interrupt();  // Reset the interrupted status
            }
            //simpleEmail("shivanii2607@gmail.com",event,"Order");
            System.out.println("Called simpleemail!!!");
    	}
        
    }
    
    @KafkaListener(topics = "user_topics", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeUser(RegisterEvent event) throws JsonProcessingException, MessagingException {
    	
    	LOGGER.info("Received OrderEvent: {}", event);

    	LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        Map<String,Object> model = new HashMap<>();
        model.put("message",event.getMessage());
        model.put("email", event.getEmail());
        model.put("event", "Registration");
        model.put("dateAndTime",formattedDateTime);
        model.put("from", "p87773623@gmail.com,e-Mail Notify");
        ordermailRepository.save(new Ordermail(event.getEmail(), model,false,"low"));
        simpleEmail(event.getEmail(),"Registration Successful",model);
    	System.out.println(event);

//        System.out.println("Received message from user_topic: " + message);

    }
    
    @KafkaListener(topics = "bulk_notification", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeBulkNotification(Event event) throws JsonProcessingException, MessagingException {
    	
    	LOGGER.info("Received Bulk Notification: {}", event);
    	LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
    	 Map<String,Object> model = new HashMap<>();
         model.put("message",event.getMessage());
         model.put("email", event.getEmail());
         model.put("event", "Promotion");
         model.put("dateAndTime",formattedDateTime);
         model.put("from", "p87773623@gmail.com,e-Mail Notify");
         
         ordermailRepository.save(new Ordermail(event.getEmail(),model,false,"low"));
         simpleEmail(event.getEmail(), "Promotions", model);

    }

    
    
    public void simpleEmail(String toEmail,String Subject,Map<String,Object> model) throws MessagingException{


    	MimeMessage message = mailSender.createMimeMessage();
    	try {
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        // add attachment
    //    helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

        
        Template t; 
        if(Subject=="Order") {
        	t = config.getTemplate("email-template.ftl");
        }else if(Subject=="Registration") {
        	t = config.getTemplate("register-template.ftl");
        }else {
        	t = config.getTemplate("promotion-template.ftl");
        }
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
        helper.setFrom("p87773623@gmail.com","e-Mail Notify");
        helper.setTo(toEmail);
        helper.setText(html,true);
        helper.setSubject(Subject);

        mailSender.send(message);


        System.out.println("Mail Send....");
    	}
    	catch(IOException|TemplateException e) {
    		System.out.println("Failed");
    	}

    }
}

