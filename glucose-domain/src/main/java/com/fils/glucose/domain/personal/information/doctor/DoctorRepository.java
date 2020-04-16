package com.fils.glucose.domain.personal.information.doctor;

import java.util.Optional;
import com.fils.glucose.domain.users.Users;

public interface DoctorRepository {
	Optional<Doctor> findById(Long doctorId);
	Optional<Users> findByUsername(String username);
}