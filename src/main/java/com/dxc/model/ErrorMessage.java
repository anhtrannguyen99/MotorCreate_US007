package com.dxc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ErrorMessage")
public class ErrorMessage{
	@Id
	@Column(name = "error")
	private String error;
	
	@Column(name = "description")
	private String description;
	
	public ErrorMessage() {
		
	}

	public ErrorMessage(String error, String description) {
		super();
		this.error = error;
		this.description = description;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}