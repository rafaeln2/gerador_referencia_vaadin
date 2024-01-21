package com.application.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.nimbusds.jose.shaded.gson.annotations.SerializedName;

public class Published {
	
	@SerializedName("date-parts")
	private ArrayList<ArrayList<Integer>> dateParts;
	
	public Published() {
		super();
	}

	public ArrayList<ArrayList<Integer>> getDateParts() {
		return dateParts;
	}

	public void setDateParts(ArrayList<ArrayList<Integer>> dateParts) {
		this.dateParts = dateParts;
	}
	
}
