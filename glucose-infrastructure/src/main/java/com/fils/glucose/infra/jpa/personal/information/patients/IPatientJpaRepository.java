package com.fils.glucose.infra.jpa.personal.information.patients;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fils.glucose.domain.personal.information.patient.Patient;

public interface IPatientJpaRepository extends JpaRepository<Patient, Long> {

	@Query("SELECT p.birthdate FROM Patient p where p.id = :id")
	Optional<LocalDate> findBirthdateById(@Param("id") Long id);
}
