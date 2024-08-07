package com.gerador.models.dtos;

import java.util.ArrayList;

import com.nimbusds.jose.shaded.gson.annotations.SerializedName;

public class PublishedDTO {
	
	@SerializedName("date-parts")
	private ArrayList<ArrayList<Integer>> dateParts;
	
	public PublishedDTO() {
		super();
	}

	public ArrayList<ArrayList<Integer>> getDateParts() {
		return dateParts;
	}

	public void setDateParts(ArrayList<ArrayList<Integer>> dateParts) {
		this.dateParts = dateParts;
	}
	
}
