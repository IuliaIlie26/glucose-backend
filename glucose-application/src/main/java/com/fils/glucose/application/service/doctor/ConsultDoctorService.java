package com.fils.glucose.application.service.doctor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fils.glucose.domain.ddd.DDD;
import com.fils.glucose.domain.doctor.Doctor;
import com.fils.glucose.domain.doctor.DoctorId;
import com.fils.glucose.domain.repository.DoctorRepository;
import static java.util.Objects.requireNonNull;

@DDD.ApplicationService
@Service
@Transactional
public class ConsultDoctorService {

	private DoctorRepository doctorRepository;
	public ConsultDoctorService() {}

	public ConsultDoctorService(DoctorRepository doctorRepository) {
		this.doctorRepository = requireNonNull(doctorRepository);
	}

	public Doctor findDoctorIdByUsername(String doctorIdAsString) {
		DoctorId doctorId = new DoctorId(doctorIdAsString);  
		return doctorRepository.findById(doctorId);
	}

}
