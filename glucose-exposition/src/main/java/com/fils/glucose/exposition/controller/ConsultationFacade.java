package com.fils.glucose.exposition.controller;

import java.time.LocalDate;
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
import com.fils.glucose.application.service.schedule.CrudScheduleService;
import com.fils.glucose.domain.consultations.Consultation;
import com.fils.glucose.domain.personal.information.doctor.Doctor;
import com.fils.glucose.domain.schedule.DailySchedule;
import com.fils.glucose.exposition.dto.ConsultationDto;
import com.fils.glucose.exposition.dto.ConsultationSpotDto;

@Service
public class ConsultationFacade {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private final CrudDoctorService crudDoctorService;
	private final CrudScheduleService crudScheduleService;
	private final CrudConsultationService crudConsultationService;

	public ConsultationFacade(CrudDoctorService crudDoctorService, CrudScheduleService crudScheduleService,
			CrudConsultationService crudConsultationService) {
		this.crudDoctorService = crudDoctorService;
		this.crudScheduleService = crudScheduleService;
		this.crudConsultationService = crudConsultationService;
	}

	public List<ConsultationSpotDto> getFreeSpots(ConsultationDto filter) {
		List<Doctor> doctors = crudDoctorService.getDoctorsBySpeciality(filter.speciality);
		return doctors.stream().map(doctor -> findAllFreeSpotsForDoctorInDateRange(filter, doctor))
				.flatMap(List::stream).collect(Collectors.toList());
	}

	public void saveConsultation(ConsultationDto consultation) {
		Consultation consultationBean = new Consultation(consultation.doctorId, consultation.patientCnp,
				LocalTime.parse(consultation.start, DateTimeFormatter.ofPattern("HH:mm")),
				LocalDate.parse(consultation.day, FORMATTER));
		crudConsultationService.save(consultationBean);
	}

	private List<ConsultationSpotDto> findAllFreeSpotsForDoctorInDateRange(ConsultationDto filter, Doctor doctor) {
		List<ConsultationSpotDto> spots = new ArrayList<>();
		LocalDate startDate = LocalDate.parse(filter.start, FORMATTER);
		LocalDate endDate = LocalDate.parse(filter.end, FORMATTER);

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

	private void checkForOpenSpots(List<ConsultationSpotDto> spots, Doctor doctor, LocalDate date,
			DailySchedule schedule) {

		Optional<LocalTime> startTime = schedule.getStart();
		Optional<LocalTime> endTime = schedule.getEnd();

		if (startTime.isPresent() && endTime.isPresent()) {
			LocalTime iterator = startTime.get();
			while (iterator.isBefore(endTime.get()) && (iterator.plusMinutes(30)).isBefore(endTime.get())) {
				if (!crudConsultationService.findByDoctorIdAndStartAndDay(doctor.getId(), iterator, date).isPresent()) {
					ConsultationSpotDto spot = createNewSpot(doctor, date, iterator);
					spots.add(spot);
				}
				iterator = iterator.plusMinutes(30);
			}
		}
	}

	private ConsultationSpotDto createNewSpot(Doctor doctor, LocalDate date, LocalTime iterator) {
		ConsultationSpotDto spot = new ConsultationSpotDto();
		spot.doctorLastName = doctor.getLastName();
		spot.doctorName = doctor.getFirstName();
		spot.date = date.format(FORMATTER);
		spot.start = iterator.format(DateTimeFormatter.ofPattern("HH:mm"));
		spot.speciality = doctor.getMedicalSpeciality();
		spot.doctorId=doctor.getId();
		return spot;
	}

}
