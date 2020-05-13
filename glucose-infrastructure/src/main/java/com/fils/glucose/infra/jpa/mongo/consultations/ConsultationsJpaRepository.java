package com.fils.glucose.infra.jpa.mongo.consultations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.fils.glucose.domain.consultations.Consultation;
import com.fils.glucose.domain.consultations.ConsultationRepository;

@Repository
public class ConsultationsJpaRepository implements ConsultationRepository {

	private final IConsultationRepository consultationRepository;

	public ConsultationsJpaRepository(IConsultationRepository consultationRepository) {
		this.consultationRepository = consultationRepository;
	}

	@Override
	public Optional<Consultation> findByDoctorIdAndStartAndDay(Long doctorId, LocalTime start, LocalDate date) {
		return consultationRepository.findByDoctorIdAndStartAndDay(doctorId, date, start);
	}

	@Override
	public void save(Consultation consultation) {
		consultationRepository.save(consultation);
		
	}

}
