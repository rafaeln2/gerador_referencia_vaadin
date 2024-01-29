package com.application.dto;

import java.util.List;

import com.nimbusds.jose.shaded.gson.annotations.SerializedName;

public class WorkMessageDTO {
	private List<String> title;
	
	private PublishedDTO published;
	
	private String volume;
	
	private List<AuthorDTO> author;
	
	@SerializedName("container-title")
	private List<String> containerTitle;
	
	@SerializedName("journal-issue")
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

}
