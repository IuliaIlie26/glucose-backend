package com.fils.glucose.application.service.schedule;

import org.springframework.stereotype.Service;

import com.fils.glucose.domain.schedule.DoctorSchedule;
import com.fils.glucose.domain.schedule.DoctorScheduleRepository;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

@Service
public class CrudScheduleService {

	private final DoctorScheduleRepository doctorScheduleRepository;

	public CrudScheduleService(DoctorScheduleRepository doctorScheduleRepository) {
		this.doctorScheduleRepository = requireNonNull(doctorScheduleRepository);
	}

	public DoctorSchedule findById(Long id) {

		Optional<DoctorSchedule> schedule = doctorScheduleRepository.findById(id);
		if (schedule.isPresent()) {
			return schedule.get();
		}
		return new DoctorSchedule();
	}

	public void save(DoctorSchedule schedule) {
		doctorScheduleRepository.save(schedule);
	}
}