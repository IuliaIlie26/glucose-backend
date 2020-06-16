package com.fils.glucose.exposition.mappers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.fils.glucose.domain.sensor.GlycemiaValues;
import com.fils.glucose.exposition.dto.GlycemiaValuesDto;

@Service
public class GlycemiaValuesMapperService {

	public GlycemiaValuesDto mapFromDomain(GlycemiaValues bean) {
		GlycemiaValuesDto dto = new GlycemiaValuesDto();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		dto.sensorId = bean.getSensorId();
		dto.timestamp = bean.getTimestamp().format(formatter);
		double value = Double.parseDouble(bean.getValue());
		dto.value = BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).toPlainString();

		return dto;
	}
}
