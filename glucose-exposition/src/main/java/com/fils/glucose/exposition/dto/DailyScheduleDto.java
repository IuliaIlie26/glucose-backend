package com.fils.glucose.exposition.dto;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DailyScheduleDto {

	@JsonProperty
	public LocalTime start;
	@JsonProperty
	public LocalTime end;

	public DailyScheduleDto() {
	}

	public DailyScheduleDto(LocalTime start, LocalTime end) {
		this.start = start;
		this.end = end;
	}

}
