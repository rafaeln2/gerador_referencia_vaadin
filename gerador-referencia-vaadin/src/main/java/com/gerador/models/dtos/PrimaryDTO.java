package com.gerador.models.dtos;

import com.nimbusds.jose.shaded.gson.annotations.SerializedName;

public class PrimaryDTO {
	@SerializedName("URL")
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
