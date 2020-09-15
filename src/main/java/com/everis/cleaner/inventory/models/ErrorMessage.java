package com.everis.cleaner.inventory.models;

import java.io.Serializable;

public class ErrorMessage implements Serializable {	
	
	private static final long serialVersionUID = 1L;
	
	private String description;
	private int statusCode;
	
	public ErrorMessage() {
		
	}
		
	public ErrorMessage(String description, int statusCode) {		
		this.description = description;
		this.statusCode = statusCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
}
