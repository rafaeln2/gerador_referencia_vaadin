package com.gerador.models.dtos.agency;

public class MessageAgencyDTO {
	private String DOI;
	private AgencyDTO agency;
	
	public MessageAgencyDTO() {
		super();
	}
	public String getDOI() {
		return DOI;
	}
	public void setDOI(String dOI) {
		DOI = dOI;
	}
	public AgencyDTO getAgency() {
		return agency;
	}
	public void setAgency(AgencyDTO agency) {
		this.agency = agency;
	}
	
}
