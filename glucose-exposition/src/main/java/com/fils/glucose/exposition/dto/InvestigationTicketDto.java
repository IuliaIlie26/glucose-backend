package com.fils.glucose.exposition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InvestigationTicketDto {
	@JsonProperty
	public String consultationId;
	@JsonProperty
	public String investigationsList;
}
