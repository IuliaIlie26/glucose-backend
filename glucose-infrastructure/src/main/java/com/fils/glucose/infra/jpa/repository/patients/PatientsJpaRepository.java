package com.fils.glucose.infra.jpa.repository.patients;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import com.fils.glucose.domain.ddd.DDD;
import com.fils.glucose.domain.patient.Patient;
import com.fils.glucose.domain.patient.PatientId;
import com.fils.glucose.domain.repository.PatientsRepository;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

@DDD.DomainRepositoryImpl
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

	public Optional<Patient> findById(PatientId id) {
		return patientsRepository.findById(id);
	}
}
