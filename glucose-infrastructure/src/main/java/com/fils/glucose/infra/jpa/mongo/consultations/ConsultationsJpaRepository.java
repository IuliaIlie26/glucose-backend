package com.fils.glucose.infra.jpa.mongo.consultations;

import java.time.LocalDateTime;
import java.util.List;
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
	public Optional<Consultation> findByDoctorIdAndStartAndDay(Long doctorId, LocalDateTime consultationDate) {
		return consultationRepository.findByDoctorIdAndConsultationDate(doctorId, consultationDate);
	}

	@Override
	public void save(Consultation consultation) {
		consultationRepository.save(consultation);

	}

	@Override
	public List<Consultation> findAll() {
		return consultationRepository.findAll();
	}

	@Override
	public void deleteByDoctorIdAndPatientIdAndConsultationDate(Long doctorId, Long patientId,
			LocalDateTime consultationDate) {
		consultationRepository.deleteByDoctorIdAndPatientIdAndConsultationDate(doctorId, patientId, consultationDate);
	}

	@Override
	public List<Consultation> findByDoctorId(Long doctorId) {
		return consultationRepository.findByDoctorId(doctorId);
	}

	@Override
	public List<Consultation> findByPatientId(Long patientId) {
		return consultationRepository.findByPatientId(patientId);
	}

	@Override
	public Optional<Consultation> findById(String consultationId) {
		return consultationRepository.findById(consultationId);
	}
}
