package com.application.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("date-parts")
public class DatePartsDTO {
	
	private List<Integer> dateParts;
	
	public DatePartsDTO() {
		super();
	}

	public List<Integer> getDateParts() {
		return dateParts;
	}

	public void setDateParts(List<Integer> dateParts) {
		this.dateParts = dateParts;
	}
}
