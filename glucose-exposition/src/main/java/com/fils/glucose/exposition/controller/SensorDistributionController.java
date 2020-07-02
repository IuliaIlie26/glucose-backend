package com.fils.glucose.exposition.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fils.glucose.exposition.dto.GlucoseFilterDto;
import com.fils.glucose.exposition.dto.GlycemiaValuesDto;
import com.fils.glucose.exposition.dto.MessageDto;
import com.fils.glucose.exposition.dto.SensorDistributionDto;
import com.fils.glucose.exposition.facade.SensorDistributionFacade;

@RestController
@RequestMapping("api/sensor-distribution")
@CrossOrigin(origins = "http://localhost:2020")
public class SensorDistributionController {

	private final SensorDistributionFacade sensorDistributionFacade;

	public SensorDistributionController(SensorDistributionFacade sensorDistributionFacade) {
		this.sensorDistributionFacade = sensorDistributionFacade;
	}

	@PostMapping("assignSensor")
	public MessageDto assignSensor(@RequestBody SensorDistributionDto dto) {
		return sensorDistributionFacade.assignSensor(dto);
	}

	@GetMapping("getSensorDistribution")
	public List<SensorDistributionDto> getSensorDistribution() {
		return sensorDistributionFacade.getSensorDistribution();
	}

	@GetMapping("getSensorStatus")
	public SensorDistributionDto getSensorStatus(@RequestParam Long patientId) {
		return sensorDistributionFacade.getSensorStatus(patientId);
	}

	@PostMapping("activateSensor")
	public SensorDistributionDto activateSensor(@RequestBody SensorDistributionDto dto) {
		return sensorDistributionFacade.activateSensor(dto);
	}

	@PostMapping("deactivateSensor")
	public SensorDistributionDto deactivateSensor(@RequestBody SensorDistributionDto dto) {
		return sensorDistributionFacade.deactivateSensor(dto);
	}
	
	@GetMapping("getGlycemiaForPatient")
	public List<GlycemiaValuesDto> getGlycemiaForPatient(@RequestParam Long patientId){
		return sensorDistributionFacade.getGlycemiaForPatient(patientId);
	}
	
	@PostMapping("getGlycemiaForPatientAndDate")
	public List<GlycemiaValuesDto> getGlycemiaForPatientAndDate(@RequestBody GlucoseFilterDto filter){
		return sensorDistributionFacade.getGlycemiaForPatientAndDate(filter);
	}
}
