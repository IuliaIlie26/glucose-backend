package com.fils.glucose.exposition.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fils.glucose.application.service.doctor.CrudDoctorService;
import com.fils.glucose.application.service.schedule.CrudScheduleService;
import com.fils.glucose.domain.personal.information.doctor.DailySchedule;
import com.fils.glucose.domain.personal.information.doctor.Doctor;
import com.fils.glucose.domain.personal.information.doctor.DoctorSchedule;
import com.fils.glucose.exposition.dto.DailyScheduleDto;
import com.fils.glucose.exposition.dto.DoctorDto;
import com.fils.glucose.exposition.dto.DoctorScheduleDto;
import static java.util.Objects.requireNonNull;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DoctorFacade {

	private final CrudDoctorService crudDoctorService;
	private final DoctorMapperService doctorMapper;
	private final CrudScheduleService crudScheduleService;

	public DoctorFacade(CrudDoctorService crudDoctorService, DoctorMapperService doctorMapperService,
			CrudScheduleService scheduleService) {
		this.crudDoctorService = requireNonNull(crudDoctorService);
		this.doctorMapper = requireNonNull(doctorMapperService);
		this.crudScheduleService = requireNonNull(scheduleService);
	}

	public DoctorDto findDoctorByUsername(String username) {
		Doctor doctor = crudDoctorService.findDoctorByUsername(username);
		return doctorMapper.mapFromDomain(doctor);
	}

	public String getDoctorNameAndLastname(Long id) {
		Doctor doctor = crudDoctorService.findDoctorById(id);
		return doctor.getFirstName() + " " + doctor.getLastName();
	}

	public List<DoctorDto> getDoctorsList() {
		List<Doctor> doctors = crudDoctorService.findAll();
		return doctors.stream().map(doctorMapper::mapFromDomain).collect(Collectors.toList());
	}

	public void saveDoctor(DoctorDto dto) {
		Doctor doc = doctorMapper.mapToDomain(dto);
		crudDoctorService.save(doc);

	}

	public void updateDoctor(DoctorDto dto) {

		Doctor newDoctor = updateOldDoctor(dto);
		crudDoctorService.save(newDoctor);
	}

	private Doctor updateOldDoctor(DoctorDto dto) {
		Long doctorId = Long.parseLong(dto.id);
		Doctor doctor = crudDoctorService.findDoctorById(doctorId);
		doctor.setEmail(dto.email);
		doctor.setFirstName(dto.name);
		doctor.setLastName(dto.lastname);
		doctor.setMedicalSpeciality(dto.speciality);
		doctor.setPhoneNumber(dto.phone);
		return doctor;
	}

	public DoctorScheduleDto getScheduleForDoctor(Long doctorId) {
		DoctorSchedule schedule = crudScheduleService.findById(doctorId);
		DoctorScheduleDto dto = new DoctorScheduleDto();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		dto.schedule = schedule.getSchedule().entrySet().stream()
				.map(entry -> new DailyScheduleDto(entry.getKey(),
						entry.getValue().getStart().map(e -> e.format(formatter)).orElse(""),
						entry.getValue().getEnd().map(e -> e.format(formatter)).orElse("")))
				.collect(Collectors.toList());
		return dto;
	}

	public void saveSchedule(DoctorScheduleDto dto) {
		DoctorSchedule schedule = new DoctorSchedule();
		schedule.setDoctorId(dto.doctorId);
		Map<Integer, DailySchedule> map = new HashMap<>();
		dto.schedule.stream().forEach(element -> addToMap(map, element));
		schedule.setSchedule(map);
		crudScheduleService.save(schedule);

	}

	private void addToMap(Map<Integer, DailySchedule> map, DailyScheduleDto element) {
		DailySchedule dailySchedule;
		if (StringUtils.isEmpty(element.start) || StringUtils.isEmpty(element.end)) {
			dailySchedule = new DailySchedule();
		} else {
			dailySchedule = new DailySchedule(OffsetTime.parse(element.start).toLocalTime(),
					OffsetTime.parse(element.end).toLocalTime());
		}
		map.put(element.dayOfWeek, dailySchedule);
	}
}
