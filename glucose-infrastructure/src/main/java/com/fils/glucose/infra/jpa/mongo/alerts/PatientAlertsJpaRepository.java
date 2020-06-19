package com.fils.glucose.infra.jpa.mongo.alerts;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fils.glucose.domain.alerts.PatientAlerts;
import com.fils.glucose.domain.alerts.PatientAlertsRepository;

@Repository
public class PatientAlertsJpaRepository implements PatientAlertsRepository {

	private final IPatientAlertsRepository patientAlertsRepo;

	public PatientAlertsJpaRepository(IPatientAlertsRepository patientAlertsRepo) {
		this.patientAlertsRepo = patientAlertsRepo;
	}

	@Override
	public void save(PatientAlerts alert) {
		patientAlertsRepo.save(alert);

	}

	@Override
	public List<PatientAlerts> findByPatientId(Long patientId) {
		return patientAlertsRepo.findByPatientId(patientId);
	}

	@Override
	public void deleteDisplayAlerts(PatientAlerts alert) {
		alert.setDisplayDeleted(true);
		patientAlertsRepo.save(alert);
	}
}
