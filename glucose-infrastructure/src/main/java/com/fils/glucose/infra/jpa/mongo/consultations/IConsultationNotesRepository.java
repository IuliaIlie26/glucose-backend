package com.fils.glucose.infra.jpa.mongo.consultations;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fils.glucose.domain.consultations.ConsultationNotes;

public interface IConsultationNotesRepository extends MongoRepository<ConsultationNotes, String>{

	Optional<ConsultationNotes> findByConsultationId(String consultationId);

}
