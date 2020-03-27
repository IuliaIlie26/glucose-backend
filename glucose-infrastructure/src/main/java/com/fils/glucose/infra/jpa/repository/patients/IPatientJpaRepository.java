package com.fils.glucose.infra.jpa.repository.patients;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fils.glucose.domain.patient.Patient;
import com.fils.glucose.domain.patient.PatientId;

public interface IPatientJpaRepository extends JpaRepository<Patient, PatientId> {
}
