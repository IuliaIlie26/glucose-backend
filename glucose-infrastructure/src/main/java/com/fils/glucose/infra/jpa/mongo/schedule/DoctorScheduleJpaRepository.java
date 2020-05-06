package com.fils.glucose.infra.jpa.mongo.schedule;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.fils.glucose.domain.personal.information.doctor.DoctorSchedule;
import com.fils.glucose.domain.personal.information.doctor.DoctorScheduleRepository;
import static java.util.Objects.requireNonNull;

@Repository
public class DoctorScheduleJpaRepository implements DoctorScheduleRepository {

	private final IDoctorScheduleRepository doctorScheduleRepository;

	public DoctorScheduleJpaRepository(IDoctorScheduleRepository doctorScheduleRepository) {
		this.doctorScheduleRepository = requireNonNull(doctorScheduleRepository);
	}

	@Override
	public Optional<DoctorSchedule> findById(Long id) {
		return doctorScheduleRepository.findById(id);
	}
}
