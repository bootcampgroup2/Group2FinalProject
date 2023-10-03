package com.model;

public class RegisterEvent {
    private String email;
    private String message;
    private String dateAndTime;
    public RegisterEvent() {
    }

    public RegisterEvent(String email, String message,String dateAndTime) {
        this.email = email;
        this.message = message;
        this.dateAndTime = dateAndTime;
    }

    public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
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

    @Override
    public String toString() {
        return "RegisterEvent{" +
                "email='" + email + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}