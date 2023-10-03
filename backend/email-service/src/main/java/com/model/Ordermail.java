package com.model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Ordermail {
    @Id
    private String id;
    @Field
    private String email;
    @Field
    private Map<String,Object> message;
    @Field
    private Boolean read;
    @Field 
    private String priority;

    public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Ordermail() {
    }

    public Ordermail(String email, Map<String,Object> message, Boolean read, String priority) {
        this.email = email;
        this.message = message;
        this.read = read;
        this.priority = priority;
        
        System.out.println(this.priority);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String,Object> getMessage() {
        return message;
    }

    public void setMessage(Map<String,Object> message) {
        this.message = message;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }
}
