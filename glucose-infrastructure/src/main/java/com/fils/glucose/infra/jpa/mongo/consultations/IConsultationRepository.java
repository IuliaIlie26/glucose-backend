package com.fils.glucose.infra.jpa.mongo.consultations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fils.glucose.domain.consultations.Consultation;

public interface IConsultationRepository extends MongoRepository<Consultation, Long>{
	
	Optional<Consultation> findByDoctorIdAndConsultationDate(Long doctorId, LocalDateTime consultationDate);

	void deleteByDoctorIdAndPatientIdAndConsultationDate(Long doctorId, Long patientId,
			LocalDateTime consultationDate);

	List<Consultation> findByDoctorId(Long doctorId);
}
