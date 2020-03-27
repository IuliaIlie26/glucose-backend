package com.fils.glucose.domain.repository;

import com.fils.glucose.domain.ddd.DDD;
import com.fils.glucose.domain.doctor.Doctor;
import com.fils.glucose.domain.doctor.DoctorId;

@DDD.DomainRepository
public interface DoctorRepository {

	Doctor findById(DoctorId doctorId);
}
