package com.fils.glucose.domain.personal.information.doctor;

import java.util.Optional;

public interface DoctorRepository {
	Optional<Doctor> findById(String doctorId);
	Optional<String> findUserIdByUsername(String userId);
}
