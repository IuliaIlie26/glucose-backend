package com.fils.glucose.domain.alerts;

import java.util.List;

public interface PatientAlertsRepository {

	public void save(PatientAlerts alert);

	public List<PatientAlerts> findByPatientId(Long patientId);

	public void deleteDisplayAlerts(PatientAlerts alert);
}
