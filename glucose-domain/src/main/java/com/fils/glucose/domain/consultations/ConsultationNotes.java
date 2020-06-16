package com.fils.glucose.domain.consultations;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="consultation_notes")
public class ConsultationNotes {

	private String consultationId;
	private String investigationTicketId;
	private String history;
	private String symptoms;
	private String diagnosis;
	private String recommendations;
	private String notes;

	public String getConsultationId() {
		return consultationId;
	}

	public void setConsultationId(String consultationId) {
		this.consultationId = consultationId;
	}

	public String getInvestigationTicketId() {
		return investigationTicketId;
	}

	public void setInvestigationTicketId(String investigationTicketId) {
		this.investigationTicketId = investigationTicketId;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getRecommendations() {
		return recommendations;
	}

	public void setRecommedations(String recommendations) {
		this.recommendations = recommendations;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
