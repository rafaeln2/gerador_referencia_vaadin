package com.gerador.models.dtos.type;


public class ResponseTypeDTO {
	private String status;
	private MessageTypeDTO message;

	public ResponseTypeDTO() {
		super();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public MessageTypeDTO getMessage() {
		return message;
	}

	public void setMessage(MessageTypeDTO message) {
		this.message = message;
	}
}
