package com.fils.glucose.domain.investigations;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "investigations")
public class Investigation {

	@Id
	private String id;
	private String ticketId;
	private String investigationName;
	private String investigationValue;
	private LocalDate date;

	public String getId() {
		return id;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getInvestigationName() {
		return investigationName;
	}

	public void setInvestigationName(String investigationName) {
		this.investigationName = investigationName;
	}

	public String getInvestigationValue() {
		return investigationValue;
	}

	public void setInvestigationValue(String investigationValue) {
		this.investigationValue = investigationValue;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
