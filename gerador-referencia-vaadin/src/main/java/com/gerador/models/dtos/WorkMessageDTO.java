package com.gerador.models.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nimbusds.jose.shaded.gson.annotations.SerializedName;

public class WorkMessageDTO {
	private List<AuthorDTO> author;
	
	private List<String> title;
	
	private List<String> subtitle;
	
	private String publisher;
	
	private PublishedDTO published;
	
	@JsonProperty("DOI")
	private String doi;
	
	private ResourceDTO resource;
	
	
	
	private String volume;
	
	@JsonProperty("container-title")
	private List<String> containerTitle;
	
	@JsonProperty("journal-issue")
	private JournalIssueDTO journalIssue;
	
	private String page;
	
	private String type;

	public WorkMessageDTO() {
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

	public PublishedDTO getPublished() {
		return published;
	}

	public void setPublished(PublishedDTO published) {
		this.published = published;
	}

	public List<AuthorDTO> getAuthor() {
		return author;
	}

	public void setAuthor(List<AuthorDTO> author) {
		this.author = author;
	}

	public List<String> getContainerTitle() {
		return containerTitle;
	}

	public void setContainerTitle(List<String> containerTitle) {
		this.containerTitle = containerTitle;
	}

	public JournalIssueDTO getJournalIssue() {
		return journalIssue;
	}

	public void setJournalIssue(JournalIssueDTO journalIssue) {
		this.journalIssue = journalIssue;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(List<String> subtitle) {
		this.subtitle = subtitle;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public ResourceDTO getResource() {
		return resource;
	}

	public void setResource(ResourceDTO resource) {
		this.resource = resource;
	}

}
