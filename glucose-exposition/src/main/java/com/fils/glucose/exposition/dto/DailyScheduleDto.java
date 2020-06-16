package com.fils.glucose.exposition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DailyScheduleDto {

	@JsonProperty
	public Integer dayOfWeek;
	@JsonProperty
	public String start;
	@JsonProperty
	public String end;

	public DailyScheduleDto() {
	}

	public DailyScheduleDto(Integer dayOfWeek, String start, String end) {
		this.dayOfWeek=dayOfWeek;
		this.start = start;
		this.end = end;
	}

}
