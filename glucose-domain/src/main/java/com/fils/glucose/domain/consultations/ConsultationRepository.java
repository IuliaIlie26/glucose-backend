package com.fils.glucose.domain.consultations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ConsultationRepository {

	public void save(Consultation consultation);
	Optional<Consultation> findByDoctorIdAndStartAndDay(Long doctorId, LocalDateTime consultationDate);
	public List<Consultation> findAll();
	public void deleteByDoctorIdAndPatientIdAndConsultationDate(Long doctorId, Long patientId,
			LocalDateTime consultationDate);
	public List<Consultation> findByDoctorId(Long doctorId);
	public List<Consultation> findByPatientId(Long patientId);
	public Optional<Consultation> findById(String consultationId);
}
