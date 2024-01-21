package com.application.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("date-parts")
public class DateParts {
	
	private List<Integer> dateParts;
	
	public DateParts() {
		super();
	}

	public List<Integer> getDateParts() {
		return dateParts;
	}

	public void setDateParts(List<Integer> dateParts) {
		this.dateParts = dateParts;
	}
}
