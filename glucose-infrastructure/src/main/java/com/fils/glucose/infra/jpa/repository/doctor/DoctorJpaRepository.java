package com.fils.glucose.infra.jpa.repository.doctor;


import com.fils.glucose.domain.doctor.Doctor;
import com.fils.glucose.domain.doctor.DoctorId;
import com.fils.glucose.domain.repository.DoctorRepository;

public class DoctorJpaRepository implements DoctorRepository {

	private final IDoctorJpaRepository doctorRepository;
	
	public DoctorJpaRepository(IDoctorJpaRepository doctorJpaRepository) {
		super();
		this.doctorRepository = doctorJpaRepository;
	}

	@Override
	public Doctor findById(DoctorId doctorId) {
		return doctorRepository.findDoctorById(doctorId);
	}

}
