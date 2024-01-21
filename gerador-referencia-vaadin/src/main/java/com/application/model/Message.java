package com.application.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.nimbusds.jose.shaded.gson.annotations.SerializedName;

public class Message {
	private List<String> title;
	
	private Published published;
	
	private String volume;
	
	private List<Author> author;
	
	@SerializedName("container-title")
	private List<String> containerTitle;

	public Message() {
		super();
	}

	public List<String> getTitle() {
		return title;
	}

	public void setTitle(List<String> title) {
		this.title = title;
	}


	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public Published getPublished() {
		return published;
	}

	public void setPublished(Published published) {
		this.published = published;
	}

	public List<Author> getAuthor() {
		return author;
	}

	public void setAuthor(List<Author> author) {
		this.author = author;
	}

	public List<String> getContainerTitle() {
		return containerTitle;
	}

	public void setContainerTitle(List<String> containerTitle) {
		this.containerTitle = containerTitle;
	}

}
