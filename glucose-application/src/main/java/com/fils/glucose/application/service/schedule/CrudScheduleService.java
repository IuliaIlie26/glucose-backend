package com.fils.glucose.application.service.schedule;

import org.springframework.stereotype.Service;
import static java.util.Objects.requireNonNull;
import com.fils.glucose.domain.personal.information.doctor.DoctorSchedule;
import com.fils.glucose.domain.personal.information.doctor.DoctorScheduleRepository;

@Service
public class CrudScheduleService {

	private final DoctorScheduleRepository doctorScheduleRepository;

	public CrudScheduleService(DoctorScheduleRepository doctorScheduleRepository) {
		this.doctorScheduleRepository = requireNonNull(doctorScheduleRepository);
	}

	public DoctorSchedule findById(Long id) {
		return doctorScheduleRepository.findById(id).orElse(new DoctorSchedule());
	}

	public void save(DoctorSchedule schedule) {
		doctorScheduleRepository.save(schedule);
	}

}