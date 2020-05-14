package com.fils.glucose.application.service.consultation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.fils.glucose.domain.consultations.Consultation;
import com.fils.glucose.domain.consultations.ConsultationRepository;

@Service
public class CrudConsultationService {

	private final ConsultationRepository consultationRepository;

	public CrudConsultationService(ConsultationRepository consultationRepository) {
		this.consultationRepository = consultationRepository;
	}

	public Optional<Consultation> findByDoctorIdAndStartAndDay(Long doctorId, LocalTime start, LocalDate date) {
		return consultationRepository.findByDoctorIdAndStartAndDay(doctorId, start, date);
	}

	public void save(Consultation consultation) {
		consultationRepository.save(consultation);
	} 
}
