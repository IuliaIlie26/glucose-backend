package com.fils.glucose.exposition.facade;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fils.glucose.application.service.patient.CrudPatientService;
import com.fils.glucose.application.service.sensor.SensorService;
import com.fils.glucose.domain.sensor.SensorDistribution;
import com.fils.glucose.exposition.dto.MessageDto;
import com.fils.glucose.exposition.dto.SensorDistributionDto;
import com.fils.glucose.exposition.mappers.SensorDistributionMapperService;

@Service
public class SensorDistributionFacade {
	private final SensorService sensorService;
	private final SensorDistributionMapperService sensorDistributionMapper;
	private final CrudPatientService crudPatientService;

	public SensorDistributionFacade(SensorService sensorService, SensorDistributionMapperService sensorDistributionMapper,
			CrudPatientService crudPatientService) {
		this.sensorService = requireNonNull(sensorService);
		this.crudPatientService = requireNonNull(crudPatientService);
		this.sensorDistributionMapper = requireNonNull(sensorDistributionMapper);
	}

	public MessageDto assignSensor(SensorDistributionDto dto) {

		MessageDto result = new MessageDto();

		Optional<Long> potentialPatient = crudPatientService.findIdByCnp(dto.patientCnp);
		if (!potentialPatient.isPresent()) {
			result.message = "patient.manage.sensor.assign.not.found";
			return result;
		}

		Long patientId = potentialPatient.get();
		if (sensorService.findByPatientId(patientId).isPresent()) {
			result.message = "patient.manage.sensor.assign.hasSensor";
			return result;
		}

		if (sensorService.checkIfSensorIsAlreadyAssigned(dto.sensorId)) {
			result.message = "patient.manage.sensor.assign.sensor.assigned";
			return result;
		}

		sensorService.assignSensorToPatient(dto.sensorId, patientId);

		return result;
	}

	public List<SensorDistributionDto> getSensorDistribution() {
		List<SensorDistributionDto> patientList = new ArrayList<>();
		List<SensorDistribution> sensorDistributionList = sensorService.getDistributionList();
		sensorDistributionList.stream()
				.forEach(distribution -> patientList.add(sensorDistributionMapper.mapFromDomain(distribution)));
		return patientList;
	}

	public SensorDistributionDto getSensorStatus(Long patientId) {
		SensorDistribution distribution = sensorService.findByPatientId(patientId).orElse(new SensorDistribution());
		return sensorDistributionMapper.mapFromDomain(distribution);
	}
}
