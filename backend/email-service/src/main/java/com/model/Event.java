package com.model;


public class Event {

	private String email;
	private String message;
	private boolean read;
	
	public Event() {}
	
	public Event(String email, String message, boolean read) {
		super();
		this.email = email;
		this.message = message;
		this.read = read;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isRead() {
		return read;
	}
	public void setRead(boolean read) {
		this.read = read;
	}
	@Override
	public String toString() {
		return "Event [email=" + email + ", message=" + message + ", read=" + read + "]";
	}
	
	
}
