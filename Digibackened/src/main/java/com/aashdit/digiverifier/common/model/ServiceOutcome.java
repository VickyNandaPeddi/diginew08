package com.aashdit.digiverifier.common.model;

import lombok.AllArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
public class ServiceOutcome<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2729374527236386292L;
	/**
	 * 
	 */
	
	private T data;
	private Boolean outcome;
	private String message;
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public Boolean getOutcome() {
		return outcome;
	}
	public void setOutcome(Boolean outcome) {
		this.outcome = outcome;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public ServiceOutcome(String message, String status) {
		this.message = message;
		this.status = status;
	}

	public ServiceOutcome()
	{
		this.setOutcome(true);
	}

}
