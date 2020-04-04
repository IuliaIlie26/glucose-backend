package com.fils.glucose.domain.personal.information.patient;

import java.util.Optional;

public interface PatientsRepository {

	Optional<Patient> findById(String id);
	Patient save(Patient patient);
}
