package com.fils.glucose.infra.jpa.repository.doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fils.glucose.domain.doctor.Doctor;
import com.fils.glucose.domain.doctor.DoctorId;

public interface IDoctorJpaRepository extends JpaRepository<Doctor, DoctorId>{

	Doctor findDoctorById(DoctorId doctorId);
}
