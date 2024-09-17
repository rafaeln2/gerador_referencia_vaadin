package com.gerador.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkDTO {
	private String status;
	
	@JsonProperty("message")
	private WorkMessageDTO message;

	public WorkMessageDTO getWorkMessage() {
		return message;
	}

	public void setWorkMessage(WorkMessageDTO message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
