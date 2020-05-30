package com.fils.glucose.exposition.facade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fils.glucose.application.service.consultation.CrudConsultationService;
import com.fils.glucose.application.service.doctor.CrudDoctorService;
import com.fils.glucose.application.service.patient.CrudPatientService;
import com.fils.glucose.application.service.schedule.CrudScheduleService;
import com.fils.glucose.domain.consultations.Consultation;
import com.fils.glucose.domain.personal.information.doctor.Doctor;
import com.fils.glucose.domain.personal.information.patient.Patient;
import com.fils.glucose.domain.schedule.DailySchedule;
import com.fils.glucose.exposition.dto.ConsultationFilterDto;
import com.fils.glucose.exposition.dto.PatientDto;
import com.fils.glucose.exposition.mappers.ConsultationMapperService;
import com.fils.glucose.exposition.mappers.PatientMapperService;
import com.fils.glucose.exposition.dto.ConsultationDto;

@Service
public class ConsultationFacade {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private final CrudDoctorService crudDoctorService;
	private final CrudScheduleService crudScheduleService;
	private final CrudConsultationService crudConsultationService;
	private final CrudPatientService crudPatientService;
	private final ConsultationMapperService consultationMapper;
	private final PatientMapperService patientMapperService;

	public ConsultationFacade(CrudDoctorService crudDoctorService, CrudScheduleService crudScheduleService,
			CrudConsultationService crudConsultationService, CrudPatientService crudPatientService,
			ConsultationMapperService consultationMapper, PatientMapperService patientMapperService) {
		this.crudDoctorService = crudDoctorService;
		this.crudScheduleService = crudScheduleService;
		this.crudConsultationService = crudConsultationService;
		this.crudPatientService = crudPatientService;
		this.consultationMapper = consultationMapper;
		this.patientMapperService = patientMapperService;
	}

	public List<ConsultationDto> getFreeSpots(ConsultationFilterDto filter) {

		List<Doctor> doctors = crudDoctorService.getDoctorsBySpeciality(filter.speciality);
		return doctors.stream().map(doctor -> findAllFreeSpotsForDoctorInDateRange(filter, doctor))
				.flatMap(List::stream).collect(Collectors.toList());
	}

	public void saveConsultation(ConsultationDto consultation) {

		Long patientId = this.crudPatientService.findByCnp(consultation.patientCnp).getId();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime consultationDate = LocalDateTime.parse(consultation.date + " " + consultation.startTime,
				formatter);

		Consultation consultationBean = new Consultation(consultation.doctorId, patientId, consultationDate);
		crudConsultationService.save(consultationBean);
	}

	private List<ConsultationDto> findAllFreeSpotsForDoctorInDateRange(ConsultationFilterDto filter, Doctor doctor) {
		List<ConsultationDto> spots = new ArrayList<>();
		LocalDate startDate = LocalDate.parse(filter.startDate, FORMATTER);
		LocalDate endDate = LocalDate.parse(filter.endDate, FORMATTER);

		for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {

			Map<Integer, DailySchedule> doctorSchedule = crudScheduleService.findById(doctor.getId()).getSchedule();
			if (!doctorSchedule.isEmpty()) {

				Integer dayOfWeek = date.getDayOfWeek().getValue();
				DailySchedule schedule = doctorSchedule.get(dayOfWeek);

				checkForOpenSpots(spots, doctor, date, schedule);
			}
		}

		return spots;
	}

	private void checkForOpenSpots(List<ConsultationDto> spots, Doctor doctor, LocalDate date, DailySchedule schedule) {

		Optional<LocalTime> startTime = schedule.getStart();
		Optional<LocalTime> endTime = schedule.getEnd();

		if (startTime.isPresent() && endTime.isPresent()) {
			LocalTime iterator = startTime.get();
			while (iterator.isBefore(endTime.get()) && (iterator.plusMinutes(30)).isBefore(endTime.get())) {
				if (!crudConsultationService.findByDoctorIdAndStartAndDay(doctor.getId(), iterator, date).isPresent()) {
					ConsultationDto spot = createNewSpot(doctor, date, iterator);
					spots.add(spot);
				}
				iterator = iterator.plusMinutes(30);
			}
		}
	}

	private ConsultationDto createNewSpot(Doctor doctor, LocalDate date, LocalTime iterator) {
		ConsultationDto spot = new ConsultationDto();
		spot.doctorLastName = doctor.getLastName();
		spot.doctorName = doctor.getFirstName();
		spot.date = date.format(FORMATTER);
		spot.startTime = iterator.format(DateTimeFormatter.ofPattern("HH:mm"));
		spot.speciality = doctor.getMedicalSpeciality();
		spot.doctorId = doctor.getId();
		return spot;
	}

	public List<ConsultationDto> getAllConsultations() {
		return crudConsultationService.findAll().stream().map(consultationMapper::mapFromDomain)
				.collect(Collectors.toList());
	}

	public void delete(ConsultationDto dto) {
		Long patientId = crudPatientService.findByCnp(dto.patientCnp).getId();
		Long doctorId = dto.doctorId;
		LocalDateTime consultationDate = LocalDateTime.parse(dto.date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		crudConsultationService.deleteByDoctorIdAndPatientIdAndConsultationDate(doctorId, patientId, consultationDate);

	}

	public List<PatientDto> getPatientsForDoctor(String username) {
		Long doctorId = crudDoctorService.getDoctorIdByUsername(username);
		List<Consultation> consultations = crudConsultationService.getConsultationsForDoctor(doctorId);
		return consultations.stream().map(this::extractPatientInfoFromConsultation).collect(Collectors.toList());
	}

	public PatientDto extractPatientInfoFromConsultation(Consultation consultation) {
		Patient patient = crudPatientService.getPatientById(consultation.getPatientId());
		return patientMapperService.mapFromDomain(patient);
	}

	public List<ConsultationDto> getPatientConsultations(Long patientId) {
		List<Consultation> consultations = crudConsultationService.getPatientConsultations(patientId);
		return consultations.stream()
				.filter(consultation -> consultation.getConsultationDate().isBefore(LocalDateTime.now()))
				.map(consultationMapper::mapFromDomain).collect(Collectors.toList());
	}
}
