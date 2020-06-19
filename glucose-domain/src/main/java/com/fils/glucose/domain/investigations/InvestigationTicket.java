package com.fils.glucose.domain.investigations;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "investigation_ticket")
public class InvestigationTicket {

	@Id
	private String consultationId;
	private String investigations;

	public String getInvestigations() {
		return investigations;
	}
	
	public String getConsultationId() {
		return consultationId;
	}

	public void setConsultationId(String consultationId) {
		this.consultationId = consultationId;
	}

	public void setInvestigations(String investigations) {
		this.investigations = investigations;
	}
}
