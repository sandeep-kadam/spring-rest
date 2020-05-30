package com.sk.springmvc.rest;

public class CustomerErrorResponse {

	private int statusCode;
	private String message;
	private long timestamp;
	
	public CustomerErrorResponse() {
		System.out.println("inside default constructor");
	}

	public CustomerErrorResponse(int statusCode, String message, long timestamp) {
		this.statusCode = statusCode;
		this.message = message;
		this.timestamp = timestamp;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
}
