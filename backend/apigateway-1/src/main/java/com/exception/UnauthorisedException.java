package com.exception;

public class UnauthorisedException extends Exception {

	private String message;
	public UnauthorisedException(String message) {
		super(message);
		this.message = message;
	}
	public String toString() {
		return "Error : "+message;
	}
}
