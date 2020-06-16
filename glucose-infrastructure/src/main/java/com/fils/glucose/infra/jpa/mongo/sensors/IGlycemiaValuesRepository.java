package com.fils.glucose.infra.jpa.mongo.sensors;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fils.glucose.domain.sensor.GlycemiaValues;

public interface IGlycemiaValuesRepository extends MongoRepository<GlycemiaValues, String>{
	List<GlycemiaValues> findBySensorId(String sensorId);
}
