package com.fils.glucose.domain.personal.information.patient;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PatientsRepository {

	Optional<LocalDate> findBirthdateById(Long id);

	Optional<Patient> findById(Long id);

	Patient save(Patient patient);

	Optional<Long> findIdByCnp(String cnp);

	Optional<Patient> findByEmail(String email);

	List<Patient> findAll();

	void deleteById(Long id);

	Optional<Patient> findByCnp(String cnp);
}
