package com.fils.glucose.infra.jpa.personal.information.patients;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.fils.glucose.domain.personal.information.patient.Patient;
import com.fils.glucose.domain.personal.information.patient.PatientsRepository;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

@Primary
@Repository
public class PatientsJpaRepository implements PatientsRepository {

	private final IPatientJpaRepository patientsRepository;

	public PatientsJpaRepository(IPatientJpaRepository patientsRepository) {
		this.patientsRepository = requireNonNull(patientsRepository);
	}

	public Patient save(Patient patient) {
		return patientsRepository.save(patient);
	}

	public Optional<Patient> findById(Long id) {
		return patientsRepository.findById(id);
	}
}
