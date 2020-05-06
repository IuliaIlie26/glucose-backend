package com.fils.glucose.exposition.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DoctorScheduleDto {

	@JsonProperty
	public Map<Integer, DailyScheduleDto> schedule = new HashMap<>();
}
