package com.fils.glucose.domain.pregnancy.info;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pregnancy_info")
public class PregnancyInfo {

	@Id
	private Long patientId;

	private LocalDate dueDate;

	public PregnancyInfo(Long patientId, LocalDate dueDate) {
		this.patientId = patientId;
		this.dueDate = dueDate;
	}

	public PregnancyInfo() {
	}

	public Long getPatientId() {
		return patientId;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}
}
