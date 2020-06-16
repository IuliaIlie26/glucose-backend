package com.fils.glucose.domain.consultations;

import java.util.Optional;

public interface ConsultationNotesRepository {

	public Optional<ConsultationNotes> findByConsultationId(String consultationId);
}
