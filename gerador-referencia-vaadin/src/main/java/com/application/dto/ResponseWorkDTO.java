package com.application.dto;

import com.nimbusds.jose.shaded.gson.annotations.SerializedName;

public class ResponseWorkDTO {
	private String status;
	
	@SerializedName("message")
	private WorkMessageDTO message;

	public ResponseWorkDTO() {
		super();
	}

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
