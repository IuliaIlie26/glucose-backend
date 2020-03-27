package com.fils.glucose.domain.repository;

import java.util.Optional;

import com.fils.glucose.domain.ddd.DDD;
import com.fils.glucose.domain.patient.Patient;
import com.fils.glucose.domain.patient.PatientId;

@DDD.DomainRepository
public interface PatientsRepository {

	Optional<Patient> findById(PatientId id);
	Patient save(Patient patient);
}
