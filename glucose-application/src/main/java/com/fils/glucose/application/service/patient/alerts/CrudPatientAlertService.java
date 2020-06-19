package com.fils.glucose.application.service.patient.alerts;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.fils.glucose.application.service.consultation.CrudConsultationService;
import com.fils.glucose.application.service.patient.CrudPatientService;
import com.fils.glucose.domain.alerts.PatientAlerts;
import com.fils.glucose.domain.alerts.PatientAlertsRepository;
import com.fils.glucose.domain.consultations.Consultation;
import com.fils.glucose.domain.personal.information.patient.Patient;
import com.mongodb.Function;

@Service
public class CrudPatientAlertService {

	private final PatientAlertsRepository alertsRepository;
	private final CrudConsultationService crudConsultationService;
	private final CrudPatientService crudPatientService;

	public CrudPatientAlertService(PatientAlertsRepository alertsRepository,
			CrudConsultationService crudConsultationService, CrudPatientService crudPatientService) {
		this.alertsRepository = alertsRepository;
		this.crudConsultationService = crudConsultationService;
		this.crudPatientService = crudPatientService;
	}

	public List<Patient> getAllPatientAlertsForDoctor(Long doctorId) {
		List<Patient> patients = getPatientsForDoctor(doctorId);
		return patients.stream().filter(this::hasAlerts).collect(Collectors.toList());
	}

	public boolean hasAlerts(Patient patient) {

		List<PatientAlerts> alerts = alertsRepository.findByPatientId(patient.getId());
		if (alerts.isEmpty()) {
			return false;
		}

		return alerts.stream().filter(alert -> !alert.isDisplayDeleted()).count() > 0;
	}

	private List<Patient> getPatientsForDoctor(Long doctorId) {
		List<Consultation> consultations = crudConsultationService.getConsultationsForDoctor(doctorId);
		return consultations.stream().map(cons -> crudPatientService.getPatientById(cons.getPatientId()))
				.filter(distinctByKey(Patient::getId)).collect(Collectors.toList());

	}

	private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Set<Object> seen = ConcurrentHashMap.newKeySet();
		return t -> seen.add(keyExtractor.apply(t));
	}

	public List<PatientAlerts> findByPatientId(Long patientId) {
		return alertsRepository.findByPatientId(patientId);
	}

	public void deleteAlertsForDoctor(Long doctorId) {
		List<Patient> patients = getPatientsForDoctor(doctorId);
		for (Patient pat : patients) {
			List<PatientAlerts> alerts = alertsRepository.findByPatientId(pat.getId());
			alerts.stream().forEach(alertsRepository::deleteDisplayAlerts);
		}

	}

}
