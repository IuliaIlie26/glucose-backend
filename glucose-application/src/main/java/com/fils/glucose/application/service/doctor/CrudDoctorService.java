package com.fils.glucose.application.service.doctor;

import org.springframework.stereotype.Service;
import com.fils.glucose.application.exception.TechnicalException;
import com.fils.glucose.domain.personal.information.doctor.Doctor;
import com.fils.glucose.domain.personal.information.doctor.DoctorRepository;
import com.fils.glucose.domain.users.Users;
import static java.util.Objects.requireNonNull;
import java.util.List;
import java.util.Optional;

@Service
public class CrudDoctorService {

	private final DoctorRepository doctorRepository;

	public CrudDoctorService(DoctorRepository doctorRepository) {
		this.doctorRepository = requireNonNull(doctorRepository);
	}

	public Doctor findDoctorByUsername(String username) {
		Long doctorId = findDoctorIdByUsername(username);
		return findDoctorById(doctorId).orElseThrow(() -> new TechnicalException("doctor.not.found"));
	}

	private Long findDoctorIdByUsername(String username) {
		return doctorRepository.findByUsername(username).map(Users::getId).orElseThrow(() -> new TechnicalException("doctor.not.found"));
	}

	private Optional<Doctor> findDoctorById(Long doctorId) {
		return doctorRepository.findById(doctorId);
	}

	public List<Doctor> findAll() {
		return doctorRepository.findAll();
	}
}
