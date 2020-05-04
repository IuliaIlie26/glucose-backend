package com.fils.glucose.application.service.sensor;

import org.springframework.stereotype.Service;

import com.fils.glucose.domain.sensor.SensorDistribution;
import com.fils.glucose.domain.sensor.SensorDistributionRepository;
import static java.util.Objects.requireNonNull;

@Service
public class SensorService {

	private final SensorDistributionRepository sensorDistributionRepository;

	public SensorService(SensorDistributionRepository sensorDistributionRepository) {
		this.sensorDistributionRepository = requireNonNull(sensorDistributionRepository);
	}

	public boolean checkIfPatientHasSensor(Long patientId) {
		return sensorDistributionRepository.findByPatientId(patientId).isPresent();
	}

	public boolean checkIfSensorIsAlreadyAssigned(String sensorId) {
		return sensorDistributionRepository.findBySensorId(sensorId).isPresent();
	}

	public void assignSensorToPatient(String sensorId, Long patientId) {
		sensorDistributionRepository.save(new SensorDistribution(sensorId, patientId));
	}
}
