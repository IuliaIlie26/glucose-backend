package com.fils.glucose.infra.jpa.mongo.consultations;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.fils.glucose.domain.consultations.ConsultationNotes;
import com.fils.glucose.domain.consultations.ConsultationNotesRepository;

@Repository
public class ConsultationNotesJpaRepository implements ConsultationNotesRepository {

	private IConsultationNotesRepository consultationNotesRepository;

	public ConsultationNotesJpaRepository(IConsultationNotesRepository consultationNotesRepository) {
		this.consultationNotesRepository = consultationNotesRepository;
	}

	@Override
	public Optional<ConsultationNotes> findByConsultationId(String consultationId) {
		return consultationNotesRepository.findByConsultationId(consultationId);
	}

	@Override
	public void save(ConsultationNotes notes) {
		consultationNotesRepository.save(notes);
	}

}
