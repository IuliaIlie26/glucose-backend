package com.fils.glucose.infra.jpa.sensors;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.fils.glucose.domain.sensor.SensorDistribution;

public interface ISensorDistributionRepository extends MongoRepository<SensorDistribution, String> {

	public Optional<SensorDistribution> findByPatientId(Long patientId);

}
