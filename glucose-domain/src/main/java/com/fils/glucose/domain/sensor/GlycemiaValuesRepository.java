package com.fils.glucose.domain.sensor;

import java.util.List;

public interface GlycemiaValuesRepository {

	List<GlycemiaValues> findBySensorId(String sensorId);
}
