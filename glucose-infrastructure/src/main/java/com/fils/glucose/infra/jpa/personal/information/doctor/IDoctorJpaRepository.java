package com.fils.glucose.infra.jpa.personal.information.doctor;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fils.glucose.domain.personal.information.doctor.Doctor;

public interface IDoctorJpaRepository extends JpaRepository<Doctor, Long>{

	Optional<Doctor> findById(Long doctorId);

	List<Doctor> findBySpeciality(String speciality);
	
}
