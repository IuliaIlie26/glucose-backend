package com.fils.glucose.exposition.facade;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fils.glucose.application.exception.TechnicalException;
import com.fils.glucose.application.service.doctor.CrudDoctorService;
import com.fils.glucose.application.service.patient.CrudPatientService;
import com.fils.glucose.application.service.sensor.CrudSensorService;
import com.fils.glucose.domain.sensor.SensorDistribution;
import com.fils.glucose.domain.sensor.Status;
import com.fils.glucose.exposition.dto.MessageDto;
import com.fils.glucose.exposition.dto.SensorDistributionDto;
import com.fils.glucose.exposition.mappers.SensorDistributionMapperService;

@Service
public class SensorDistributionFacade {
	private final CrudSensorService crudSensorService;
	private final SensorDistributionMapperService sensorDistributionMapper;
	private final CrudPatientService crudPatientService;
	private final CrudDoctorService crudDoctorService;

	public SensorDistributionFacade(CrudSensorService sensorService,
			SensorDistributionMapperService sensorDistributionMapper, CrudPatientService crudPatientService,
			CrudDoctorService crudDoctorService) {
		this.crudSensorService = requireNonNull(sensorService);
		this.crudPatientService = requireNonNull(crudPatientService);
		this.sensorDistributionMapper = requireNonNull(sensorDistributionMapper);
		this.crudDoctorService = requireNonNull(crudDoctorService);
	}

	public MessageDto assignSensor(SensorDistributionDto dto) {

		MessageDto result = new MessageDto();

		Optional<Long> potentialPatient = crudPatientService.findIdByCnp(dto.patientCnp);
		if (!potentialPatient.isPresent()) {
			result.message = "patient.manage.sensor.assign.not.found";
			return result;
		}

		Long patientId = potentialPatient.get();
		if (crudSensorService.findByPatientId(patientId).isPresent()) {
			result.message = "patient.manage.sensor.assign.hasSensor";
			return result;
		}

		if (crudSensorService.checkIfSensorIsAlreadyAssigned(dto.sensorId)) {
			result.message = "patient.manage.sensor.assign.sensor.assigned";
			return result;
		}

		crudSensorService.save(dto.sensorId, patientId);

		return result;
	}

	public List<SensorDistributionDto> getSensorDistribution() {
		List<SensorDistributionDto> patientList = new ArrayList<>();
		List<SensorDistribution> sensorDistributionList = crudSensorService.getDistributionList();
		sensorDistributionList.stream()
				.forEach(distribution -> patientList.add(sensorDistributionMapper.mapFromDomain(distribution)));
		return patientList;
	}

	public SensorDistributionDto getSensorStatus(Long patientId) {
		SensorDistribution distribution = crudSensorService.findByPatientId(patientId).orElse(new SensorDistribution());
		return sensorDistributionMapper.mapFromDomain(distribution);
	}

	public SensorDistributionDto activateSensor(SensorDistributionDto dto) {
		SensorDistribution distribution = getSensorDistribution(dto);
		distribution.setActivationDate(LocalDate.now());
		distribution.setStatus(Status.ACTIVE);
		crudSensorService.save(distribution);
		return sensorDistributionMapper.mapFromDomain(distribution);
	}

	public SensorDistributionDto deactivateSensor(SensorDistributionDto dto) {
		SensorDistribution distribution = getSensorDistribution(dto);
		distribution.setDeactivationDate(LocalDate.now());
		distribution.setStatus(Status.DEACTIVATED);
		crudSensorService.save(distribution);
		return sensorDistributionMapper.mapFromDomain(distribution);
	}

	private SensorDistribution getSensorDistribution(SensorDistributionDto dto) {
		Long doctorId = crudDoctorService.getDoctorIdByUsername(dto.doctorUsername);
		Long patientId = crudPatientService.findIdByCnp(dto.patientCnp)
				.orElseThrow(() -> new TechnicalException("patient.not.found"));
		SensorDistribution distribution = crudSensorService.findByPatientId(patientId)
				.orElseThrow(() -> new TechnicalException("patient.sensor.not.found"));
		distribution.setDoctorId(doctorId);
		return distribution;
	}
}
