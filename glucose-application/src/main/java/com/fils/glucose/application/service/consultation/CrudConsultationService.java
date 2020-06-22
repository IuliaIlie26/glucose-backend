package com.fils.glucose.application.service.consultation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.fils.glucose.application.exception.TechnicalException;
import com.fils.glucose.domain.consultations.Consultation;
import com.fils.glucose.domain.consultations.ConsultationNotes;
import com.fils.glucose.domain.consultations.ConsultationNotesRepository;
import com.fils.glucose.domain.consultations.ConsultationRepository;

@Service
public class CrudConsultationService {

	private final ConsultationRepository consultationRepository;
	private final ConsultationNotesRepository consultationNotesRepository;

	public CrudConsultationService(ConsultationRepository consultationRepository,
			ConsultationNotesRepository consultationNotesRepository) {
		this.consultationRepository = consultationRepository;
		this.consultationNotesRepository = consultationNotesRepository;
	}

	public Optional<Consultation> findByDoctorIdAndStartAndDay(Long doctorId, LocalTime start, LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime consultationDate = LocalDateTime.parse(date + " " + start, formatter);
		return consultationRepository.findByDoctorIdAndStartAndDay(doctorId, consultationDate);
	}

	public void save(Consultation consultation) {
		consultationRepository.save(consultation);
	}

	public List<Consultation> findAll() {
		return consultationRepository.findAll();
	}

	public void deleteByDoctorIdAndPatientIdAndConsultationDate(Long doctorId, Long patientId,
			LocalDateTime consultationDate) {
		consultationRepository.deleteByDoctorIdAndPatientIdAndConsultationDate(doctorId, patientId, consultationDate);

	}

	public List<Consultation> getConsultationsForDoctor(Long doctorId) {
		return consultationRepository.findByDoctorId(doctorId);
	}

	public List<Consultation> getPatientConsultations(Long patientId) {
		return consultationRepository.findByPatientId(patientId);
	}

	public ConsultationNotes findConsultationNotesByConsultationId(String consultationId) {
		return consultationNotesRepository.findByConsultationId(consultationId).orElse(new ConsultationNotes());
	}

	public Consultation findById(String consultationId) {
		return consultationRepository.findById(consultationId)
				.orElseThrow(() -> new TechnicalException("backend.consultation.not.found"));
	}

	public Optional<Consultation> getCurrentConsultation(Long doctorId) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime start = now.truncatedTo(ChronoUnit.HOURS).plusMinutes(30 * (now.getMinute() / 30) - 1);
		LocalDateTime nextFinish = now.truncatedTo(ChronoUnit.HOURS).plusMinutes(30 * (now.getMinute() / 30 + 1));
		return consultationRepository.findByDoctorId(doctorId).stream().filter(
				cons -> cons.getConsultationDate().isBefore(nextFinish) && cons.getConsultationDate().isAfter(start))
				.findFirst();
	}

	public void saveNotes(ConsultationNotes notes) {
		consultationNotesRepository.save(notes);

	}

	public List<Consultation> getTodaysConsultations(Long doctorId) {
		return consultationRepository.findByDoctorId(doctorId).stream()
				.filter(cons -> cons.getConsultationDate().toLocalDate().isEqual(LocalDate.now()))
				.collect(Collectors.toList());
	}

	public List<Consultation> getFutureConsultationForPatient(Long patientId) {
		return consultationRepository.findByPatientId(patientId);
	}
}
