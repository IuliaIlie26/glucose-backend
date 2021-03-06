package com.fils.glucose.exposition.facade;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.fils.glucose.application.exception.TechnicalException;
import com.fils.glucose.application.service.doctor.CrudDoctorService;
import com.fils.glucose.application.service.patient.CrudPatientService;
import com.fils.glucose.application.service.sensor.CrudSensorService;
import com.fils.glucose.domain.sensor.SensorDistribution;
import com.fils.glucose.domain.sensor.Status;
import com.fils.glucose.exposition.dto.GlucoseFilterDto;
import com.fils.glucose.exposition.dto.GlycemiaValuesDto;
import com.fils.glucose.exposition.dto.MessageDto;
import com.fils.glucose.exposition.dto.SensorDistributionDto;
import com.fils.glucose.exposition.mappers.GlycemiaValuesMapperService;
import com.fils.glucose.exposition.mappers.SensorDistributionMapperService;

@Service
public class SensorDistributionFacade {
	private final CrudSensorService crudSensorService;
	private final SensorDistributionMapperService sensorDistributionMapper;
	private final CrudPatientService crudPatientService;
	private final CrudDoctorService crudDoctorService;
	private final GlycemiaValuesMapperService glycemiaMapper;

	public SensorDistributionFacade(CrudSensorService sensorService,
			SensorDistributionMapperService sensorDistributionMapper, CrudPatientService crudPatientService,
			CrudDoctorService crudDoctorService, GlycemiaValuesMapperService glycemiaMapper) {
		this.crudSensorService = requireNonNull(sensorService);
		this.crudPatientService = requireNonNull(crudPatientService);
		this.sensorDistributionMapper = requireNonNull(sensorDistributionMapper);
		this.crudDoctorService = requireNonNull(crudDoctorService);
		this.glycemiaMapper = glycemiaMapper;
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
		SensorDistributionDto dto = new SensorDistributionDto();
		Optional<SensorDistribution> distribution = crudSensorService.findByPatientId(patientId);
		if (distribution.isPresent()) {
			dto = sensorDistributionMapper.mapFromDomain(distribution.get());
		} else {
			dto.status = Status.NOT_ASSIGNED.toString();
		}
		return dto;
	}

	public SensorDistributionDto activateSensor(SensorDistributionDto dto) {
		SensorDistribution distribution = getSensorDistribution(dto);
		distribution.setActivationDate(LocalDate.now());
		distribution.setDeactivationDate(null);
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
				.orElseThrow(() -> new TechnicalException("backend.patient.not.found"));
		SensorDistribution distribution = crudSensorService.findByPatientId(patientId)
				.orElseThrow(() -> new TechnicalException("backend.patient.sensor.not.found"));
		distribution.setDoctorId(doctorId);
		return distribution;
	}

	public List<GlycemiaValuesDto> getGlycemiaForPatient(Long patientId) {

		Optional<SensorDistribution> sensorOptional = crudSensorService.findByPatientId(patientId);
		if (sensorOptional.isPresent()) {
			SensorDistribution sensor = sensorOptional.get();
			if (sensor.getStatus() == Status.ACTIVE) {
				return crudSensorService.getGlycemiaValues(sensor.getSensorId()).stream()
						.map(glycemiaMapper::mapFromDomain).collect(Collectors.toList());
			} else {
				throw new TechnicalException("backend.sensor.not.active");
			}
		} else {
			throw new TechnicalException("backend.patient.sensor.not.found");
		}
	}

	public List<GlycemiaValuesDto> getGlycemiaForPatientAndDate(GlucoseFilterDto filter) {
		Optional<SensorDistribution> sensorOptional = crudSensorService.findByPatientId(filter.patientId);
		LocalDate date = LocalDate.parse(filter.glDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		if (sensorOptional.isPresent()) {
			SensorDistribution sensor = sensorOptional.get();
			if (sensor.getStatus() == Status.ACTIVE) {
				return crudSensorService.getGlycemiaValues(sensor.getSensorId()).stream()
						.filter(gl -> gl.getTimestamp().toLocalDate().equals(date)).map(glycemiaMapper::mapFromDomain)
						.collect(Collectors.toList());
			} else {
				throw new TechnicalException("backend.sensor.not.active");
			}
		} else {
			throw new TechnicalException("backend.patient.sensor.not.found");
		}
	}
}
