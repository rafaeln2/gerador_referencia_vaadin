package com.gerador.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrimaryDTO {
	@JsonProperty("URL")
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
