package com.fils.glucose.infra.jpa.mongo.consultations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fils.glucose.domain.consultations.Consultation;

public interface IConsultationRepository extends MongoRepository<Consultation, Long>{
	
	Optional<Consultation> findByDoctorIdAndStartAndDay(Long doctorId, LocalDate date, LocalTime start);
}
