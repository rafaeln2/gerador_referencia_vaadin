package com.gerador.models.dtos.agency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseAgencyDTO {
	private String status;
	private MessageAgencyDTO message;
	public ResponseAgencyDTO() {
		super();
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public MessageAgencyDTO getMessage() {
		return message;
	}
	public void setMessage(MessageAgencyDTO message) {
		this.message = message;
	}

}
