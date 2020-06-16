package com.fils.glucose.exposition.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DoctorScheduleDto {

	@JsonProperty
	public Long doctorId;
	@JsonProperty
	public List<DailyScheduleDto> schedule = new ArrayList<>();
	
}
