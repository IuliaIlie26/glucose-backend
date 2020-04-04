package com.fils.glucose.application.service.doctor;

import org.springframework.stereotype.Service;
import com.fils.glucose.application.exception.TechnicalException;
import com.fils.glucose.domain.personal.information.doctor.Doctor;
import com.fils.glucose.domain.personal.information.doctor.DoctorRepository;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

@Service
public class ConsultDoctorService {

	private final DoctorRepository doctorRepository;

	public ConsultDoctorService(DoctorRepository doctorRepository) {
		this.doctorRepository = requireNonNull(doctorRepository);
	}

	public Doctor findDoctorByUsername(String username) {
		String doctorId = findDoctorUsernameById(username)
				.orElseThrow(() -> new TechnicalException("doctor.not.found"));
		return findDoctorById(doctorId).orElseThrow(() -> new TechnicalException("doctor.not.found"));
	}

	private Optional<String> findDoctorUsernameById(String id) {
		return doctorRepository.findUserIdByUsername(id);
	}

	private Optional<Doctor> findDoctorById(String doctorId) {
		return doctorRepository.findById(doctorId);
	}
}
