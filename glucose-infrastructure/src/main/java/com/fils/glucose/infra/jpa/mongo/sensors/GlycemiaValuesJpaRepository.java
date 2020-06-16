package com.fils.glucose.infra.jpa.mongo.sensors;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fils.glucose.domain.sensor.GlycemiaValues;
import com.fils.glucose.domain.sensor.GlycemiaValuesRepository;

@Repository
public class GlycemiaValuesJpaRepository implements GlycemiaValuesRepository {

	private IGlycemiaValuesRepository glycemiaRepo;
	
	public GlycemiaValuesJpaRepository(IGlycemiaValuesRepository glycemiaRepo) {
		this.glycemiaRepo = glycemiaRepo;
	}

	@Override
	public List<GlycemiaValues> findBySensorId(String sensorId) {
		return glycemiaRepo.findBySensorId(sensorId);
	}

}
