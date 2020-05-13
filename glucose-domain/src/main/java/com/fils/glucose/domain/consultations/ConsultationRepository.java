package com.fils.glucose.domain.consultations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface ConsultationRepository {

	public Optional<Consultation> findByDoctorIdAndStartAndDay(Long doctorId, LocalTime start, LocalDate date);
	public void save(Consultation consultation);
}
