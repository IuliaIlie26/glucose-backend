package com.fils.glucose.application.service.sensor;

import org.springframework.stereotype.Service;

import com.fils.glucose.domain.sensor.GlycemiaValues;
import com.fils.glucose.domain.sensor.GlycemiaValuesRepository;
import com.fils.glucose.domain.sensor.SensorDistribution;
import com.fils.glucose.domain.sensor.SensorDistributionRepository;
import com.fils.glucose.domain.sensor.Status;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

@Service
public class CrudSensorService {

	private final SensorDistributionRepository sensorDistributionRepository;
	private final GlycemiaValuesRepository glycemiaRepo;

	public CrudSensorService(SensorDistributionRepository sensorDistributionRepository,
			GlycemiaValuesRepository glycemiaRepo) {
		this.sensorDistributionRepository = requireNonNull(sensorDistributionRepository);
		this.glycemiaRepo = glycemiaRepo;
	}

	public Optional<SensorDistribution> findByPatientId(Long patientId) {
		return sensorDistributionRepository.findByPatientId(patientId);
	}

	public boolean checkIfSensorIsAlreadyAssigned(String sensorId) {
		return sensorDistributionRepository.findBySensorId(sensorId).isPresent();
	}

	public void save(String sensorId, Long patientId) {
		sensorDistributionRepository.save(new SensorDistribution(sensorId, patientId, Status.INACTIVE));
	}

	public List<SensorDistribution> getDistributionList() {
		return sensorDistributionRepository.findAll();
	}

	public void save(SensorDistribution distribution) {
		sensorDistributionRepository.save(distribution);
	}

	public List<GlycemiaValues> getGlycemiaValues(String sensorId) {
		return glycemiaRepo.findBySensorId(sensorId);
	}
}
