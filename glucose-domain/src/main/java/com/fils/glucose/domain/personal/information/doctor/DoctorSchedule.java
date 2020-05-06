package com.fils.glucose.domain.personal.information.doctor;

import java.util.HashMap;
import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "doctor_schedule")
public class DoctorSchedule {

	@Id
	private Long id;

	Map<Integer, DailySchedule> schedule = new HashMap<>();

	public Map<Integer, DailySchedule> getSchedule() {
		return schedule;
	}

	public void setSchedule(Map<Integer, DailySchedule> schedule) {
		this.schedule = schedule;
	}

	public Long getDoctorId() {
		return id;
	}

	public void setDoctorId(Long doctorId) {
		this.id = doctorId;
	}
}