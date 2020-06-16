package com.fils.glucose.domain.schedule;

import java.util.Optional;

public interface DoctorScheduleRepository {

	Optional<DoctorSchedule> findById(Long id);
	void save(DoctorSchedule schedule);
}
