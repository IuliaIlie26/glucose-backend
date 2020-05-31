package com.fils.glucose.exposition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConsultationNotesDto {

	@JsonProperty
	public String consultationId;
	@JsonProperty
	public String investigationTicketId;
	@JsonProperty
	public String history;
	@JsonProperty
	public String symptoms;
	@JsonProperty
	public String diagnosis;
	@JsonProperty
	public String recommandations;
	@JsonProperty
	public String notes;
}
