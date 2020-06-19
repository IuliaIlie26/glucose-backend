package com.fils.glucose.infra.jpa.mongo.alerts;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fils.glucose.domain.alerts.PatientAlerts;

public interface IPatientAlertsRepository extends MongoRepository<PatientAlerts, String> {

	List<PatientAlerts> findByPatientId(Long patientId);

}
