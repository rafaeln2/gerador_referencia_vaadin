package com.application.dto;

public class AuthorDTO {
	private String given; // nome
	private String family; // sobrenome

	public AuthorDTO() {
		super();
	}

	public String getGiven() {
		return given;
	}

	public void setGiven(String given) {
		this.given = given;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

}
