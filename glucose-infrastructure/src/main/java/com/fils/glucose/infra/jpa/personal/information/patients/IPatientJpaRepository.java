package com.fils.glucose.infra.jpa.personal.information.patients;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fils.glucose.domain.personal.information.patient.Patient;

public interface IPatientJpaRepository extends JpaRepository<Patient, Long> {
}
