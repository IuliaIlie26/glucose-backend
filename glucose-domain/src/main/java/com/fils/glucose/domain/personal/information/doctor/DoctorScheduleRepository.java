package com.fils.glucose.domain.personal.information.doctor;

import java.util.Optional;

public interface DoctorScheduleRepository {

	Optional<DoctorSchedule> findById(Long id);
}
