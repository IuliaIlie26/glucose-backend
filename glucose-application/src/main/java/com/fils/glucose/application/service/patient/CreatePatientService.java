package com.fils.glucose.application.service.patient;

import org.springframework.stereotype.Service;
import com.fils.glucose.application.service.doctor.ConsultDoctorService;
import com.fils.glucose.domain.personal.information.doctor.Doctor;
import com.fils.glucose.domain.personal.information.patient.Patient;
import com.fils.glucose.domain.personal.information.patient.PatientsRepository;

import static java.util.Objects.requireNonNull;

@Service
public class CreatePatientService {

	private final ConsultDoctorService consultDoctorsService;
	private final PatientsRepository patientRepository;

	public CreatePatientService(ConsultDoctorService consultDoctors, PatientsRepository patientRepository) {
		this.consultDoctorsService = requireNonNull(consultDoctors);
		this.patientRepository = requireNonNull(patientRepository);
		
	}

	public Long savePatient(Patient patient, String doctorUsername) {
		Doctor doctor = consultDoctorsService.findDoctorByUsername(doctorUsername);
		patient.getDoctors().add(doctor);
		return patientRepository.save(patient).getId();
	}
}
