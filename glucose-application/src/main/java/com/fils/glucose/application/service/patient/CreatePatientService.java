package com.fils.glucose.application.service.patient;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fils.glucose.application.service.doctor.ConsultDoctorService;
import com.fils.glucose.domain.ddd.DDD;
import com.fils.glucose.domain.doctor.Doctor;
import com.fils.glucose.domain.patient.Patient;
import com.fils.glucose.domain.repository.PatientsRepository;

import static java.util.Objects.requireNonNull;

@DDD.ApplicationService
@Service
@Transactional
public class CreatePatientService {

	private final ConsultDoctorService consultDoctorsService;
	private final PatientsRepository patientRepository;

	public CreatePatientService(ConsultDoctorService consultDoctors, PatientsRepository patientRepository) {
		this.consultDoctorsService = requireNonNull(consultDoctors);
		this.patientRepository = requireNonNull(patientRepository);
	}

	public String savePatient(Patient patient, String doctorUsername) {
		return patientRepository.save(patient).getId().getValue();
	}

}
