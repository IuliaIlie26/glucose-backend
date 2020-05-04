package com.fils.glucose.domain.sensor;

import java.util.List;
import java.util.Optional;

public interface SensorDistributionRepository {

	public void save(SensorDistribution sensorDistribution);

	public Optional<SensorDistribution> findBySensorId(String sensorId);

	public Optional<SensorDistribution> findByPatientId(Long patientId);

	public List<SensorDistribution> findAll();
}
