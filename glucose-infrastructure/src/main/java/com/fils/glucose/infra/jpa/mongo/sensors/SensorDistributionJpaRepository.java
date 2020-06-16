package com.fils.glucose.infra.jpa.mongo.sensors;

import com.fils.glucose.domain.sensor.SensorDistribution;
import com.fils.glucose.domain.sensor.SensorDistributionRepository;
import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class SensorDistributionJpaRepository implements SensorDistributionRepository {

	private ISensorDistributionRepository sensorDistributionRepository;

	public SensorDistributionJpaRepository(ISensorDistributionRepository sensorDistributionRepository) {
		this.sensorDistributionRepository = requireNonNull(sensorDistributionRepository);
	}

	@Override
	public void save(SensorDistribution sensorDistribution) {
		this.sensorDistributionRepository.save(sensorDistribution);
	}

	@Override
	public Optional<SensorDistribution> findBySensorId(String sensorId) {
		return this.sensorDistributionRepository.findById(sensorId);
	}

	@Override
	public Optional<SensorDistribution> findByPatientId(Long patientId) {
		return this.sensorDistributionRepository.findByPatientId(patientId);
	}

	@Override
	public List<SensorDistribution> findAll() {
		return sensorDistributionRepository.findAll();
	}
}
