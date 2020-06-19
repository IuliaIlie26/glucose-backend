package com.fils.glucose.infra.schedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fils.glucose.domain.alerts.PatientAlerts;
import com.fils.glucose.domain.alerts.PatientAlertsRepository;
import com.fils.glucose.domain.personal.information.patient.Patient;
import com.fils.glucose.domain.personal.information.patient.PatientsRepository;
import com.fils.glucose.domain.sensor.GlycemiaValues;
import com.fils.glucose.domain.sensor.GlycemiaValuesRepository;
import com.fils.glucose.domain.sensor.SensorDistribution;
import com.fils.glucose.domain.sensor.SensorDistributionRepository;

@Component
public class AlertsScheduler {

	private final GlycemiaValuesRepository glycemiaValuesRepository;
	private final PatientsRepository patientRepo;
	private final SensorDistributionRepository distributionRepository;
	private final PatientAlertsRepository alertsRepository;

	public AlertsScheduler(GlycemiaValuesRepository glycemiaValuesRepository, PatientsRepository patientRepo,
			SensorDistributionRepository distributionRepository, PatientAlertsRepository alertsRepository) {
		this.glycemiaValuesRepository = glycemiaValuesRepository;
		this.distributionRepository = distributionRepository;
		this.patientRepo = patientRepo;
		this.alertsRepository = alertsRepository;
	}

	@Scheduled(fixedRate = 60*1000)
	public void computeAlerts() {
		List<Patient> patients = patientRepo.findAll();
		patients.stream().forEach(this::checkGlycemiaValues);

	}

	private void checkGlycemiaValues(Patient patient) {
		Optional<SensorDistribution> sensorOptional = distributionRepository.findByPatientId(patient.getId());
		if (sensorOptional.isPresent()) {
			List<GlycemiaValues> glycemia = glycemiaValuesRepository.findBySensorId(sensorOptional.get().getSensorId())
					.stream().filter(value -> value.getTimestamp().isAfter(LocalDateTime.now().minusDays(1)))
					.collect(Collectors.toList());

			long count = glycemia.stream().filter(value -> Double.parseDouble(value.getValue()) > 8.0).count();
			if (count > glycemia.size() / 2) {
				alertsRepository.save(new PatientAlerts(patient.getId(), LocalDateTime.now(), false));
			}
		}
	}
}
