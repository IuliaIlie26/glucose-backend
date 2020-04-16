package com.fils.glucose.domain.personal.information.patient;

import java.util.Optional;

public interface PatientsRepository {

	Optional<Patient> findById(Long id);
	Patient save(Patient patient);
}
