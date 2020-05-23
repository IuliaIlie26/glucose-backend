package com.fils.glucose.exposition.service;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.fils.glucose.application.service.patient.CrudPatientService;
import com.fils.glucose.application.service.pregnancy.info.PregnancyInfoService;
import com.fils.glucose.application.service.risk.factors.CrudRiskFactorsService;
import com.fils.glucose.application.service.sensor.SensorService;
import com.fils.glucose.domain.personal.information.patient.Patient;
import com.fils.glucose.domain.pregnancy.info.PregnancyInfo;
import com.fils.glucose.domain.risk.factors.RiskFactors;
import com.fils.glucose.domain.risk.factors.RiskScore;
import com.fils.glucose.domain.sensor.SensorDistribution;
import com.fils.glucose.exposition.dto.AddressDto;
import com.fils.glucose.exposition.dto.MessageDto;
import com.fils.glucose.exposition.dto.PatientDistributionDto;
import com.fils.glucose.exposition.dto.PatientDto;
import com.fils.glucose.exposition.dto.PregnancyInfoDto;
import com.fils.glucose.exposition.dto.RiskFactorsDto;
import com.fils.glucose.exposition.dto.RiskScoreDto;

@Service
public class PatientFacade {

	private final CrudPatientService crudPatientService;
	private final PatientMapperService patientMapperService;
	private final SensorService sensorService;
	private final CrudRiskFactorsService riskFactorsService;
	private final RiskFactorsMapperService riskFactorMapper;
	private final PregnancyInfoService pregnancyInfoService;
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public PatientFacade(CrudPatientService crudPatientService, PatientMapperService patientMapperService,
			SensorService sensorService, CrudRiskFactorsService riskFactorsService,
			RiskFactorsMapperService riskFactorMapper, PregnancyInfoService pregnancyInfoService) {
		this.crudPatientService = requireNonNull(crudPatientService);
		this.patientMapperService = requireNonNull(patientMapperService);
		this.sensorService = requireNonNull(sensorService);
		this.riskFactorsService = requireNonNull(riskFactorsService);
		this.riskFactorMapper = requireNonNull(riskFactorMapper);
		this.pregnancyInfoService = requireNonNull(pregnancyInfoService);
	}

	public Long savePatient(PatientDto patientDto) {
		Patient patient = patientMapperService.mapToDomain(patientDto);
		return crudPatientService.savePatient(patient);
	}

	public String getFullFormatAgeById(Long id) {
		return crudPatientService.getFullFormatAgeById(id);
	}

	public void saveRiskFactors(RiskFactorsDto riskFactorsDto) {
		RiskFactors riskFactors = patientMapperService.mapRiskFactorsToDomain(riskFactorsDto);
		crudPatientService.saveRiskFactors(riskFactors);
	}

	public List<PatientDto> getAllPatients() {
		List<Patient> allPatients = crudPatientService.getAllPatients();
		List<PatientDto> patientsSet = allPatients.stream().map(patientMapperService::mapFromDomain)
				.collect(Collectors.toList());
		patientsSet.forEach(this::computeFullAddress);
		return patientsSet;
	}

	private void computeFullAddress(PatientDto patient) {
		AddressDto address = patient.address;
		patient.fullAddress = address.addressLine1 + ", ";
		if (!StringUtils.isEmpty(address.addressLine2)) {
			patient.fullAddress += address.addressLine2 + ", ";
		}
		patient.fullAddress += address.zipCode + " " + address.city + ", " + address.region + ", " + address.country;
	}

	public void deletePatientById(String id) {
		Long idLongValue = Long.parseLong(id);
		crudPatientService.deletePatientById(idLongValue);

	}

	public PatientDto getPatientById(Long id) {
		Patient patient = crudPatientService.getPatientById(id);
		return patientMapperService.mapFromDomain(patient);
	}

	public void updatePatient(PatientDto dto) {
		LocalDate birthdate = LocalDate.parse(dto.birthdate, formatter);

		Long idLongValue = Long.parseLong(dto.id);
		Patient oldPatient = crudPatientService.getPatientById(idLongValue);
		oldPatient.setAddress(patientMapperService.mapAddressDtoToDomain(dto.address));
		oldPatient.setBirthdate(birthdate);
		oldPatient.setCnp(dto.cnp);
		oldPatient.setFirstName(dto.name);
		oldPatient.setLastName(dto.lastname);
		oldPatient.setEmail(dto.email);
		oldPatient.setPhoneNumber(dto.phone);
		crudPatientService.updatePatient(oldPatient);
	}

	public MessageDto assignSensor(PatientDistributionDto dto) {

		MessageDto result = new MessageDto();

		Optional<Long> potentialPatient = crudPatientService.findIdByCnp(dto.patientCnp);
		if (!potentialPatient.isPresent()) {
			result.message = "patient.manage.sensor.assign.not.found";
			return result;
		}

		Long patientId = potentialPatient.get();
		if (sensorService.checkIfPatientHasSensor(patientId)) {
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

	public List<PatientDistributionDto> getSensorDistribution() {
		List<PatientDistributionDto> patientList = new ArrayList<>();
		List<SensorDistribution> sensorDistributionList = sensorService.getDistributionList();
		sensorDistributionList.stream()
				.forEach(distribution -> mapToPatientDistributionAndAddToList(patientList, distribution));
		return patientList;
	}

	private void mapToPatientDistributionAndAddToList(List<PatientDistributionDto> patientList,
			SensorDistribution distribution) {
		PatientDistributionDto dto = new PatientDistributionDto();
		Patient patient = crudPatientService.getPatientById(distribution.getPatientId());
		dto.patientCnp = patient.getCnp();
		dto.patientName = patient.getFirstName() + " " + patient.getLastName();
		dto.status = distribution.getStatus().name();
		Optional<LocalDate> activationDate = distribution.getActivationDate();
		if (activationDate.isPresent()) {
			dto.activationDate = activationDate.get().format(formatter);
		}
		Optional<LocalDate> deactivationDate = distribution.getDeactivationDate();
		if (deactivationDate.isPresent()) {
			dto.deactivationDate = deactivationDate.get().format(formatter);
		}
		Optional<String> doctorId = distribution.getDoctorId();
		if (doctorId.isPresent()) {
			dto.doctorName = doctorId.get(); // TODO ia numele doctorului
		}
		dto.sensorId = distribution.getSensorId();
		patientList.add(dto);
	}

	public String getPatientNameByCnp(String cnp) {
		Patient patient = crudPatientService.findByCnp(cnp);
		return patient.getLastName() + " " + patient.getFirstName();
	}

	public RiskFactorsDto getRiskFactors(Long patientId) {
		RiskFactors risks = riskFactorsService.findById(patientId);
		return riskFactorMapper.mapFromDomain(risks);
	}

	public PregnancyInfoDto getPregancyInfo(Long patientId) {
		PregnancyInfoDto dto = new PregnancyInfoDto();
		PregnancyInfo pregnancyInfo = pregnancyInfoService.getPregancyInfoByPatientId(patientId);

		dto.dueDate = pregnancyInfo.getDueDate().format(formatter);
		dto.patientId = patientId;
		return dto;
	}

	public void savePregancyInfo(PregnancyInfoDto dto) {
		PregnancyInfo pregnancyInfo = new PregnancyInfo(dto.patientId, LocalDate.parse(dto.dueDate, formatter));
		pregnancyInfoService.save(pregnancyInfo);
	}

	public RiskScoreDto calculateRiskScore(Long patientId) {
		
		RiskScore score = riskFactorsService.calculateRiskScore(patientId);
		RiskScoreDto dto = new RiskScoreDto();
		dto.caliskanScore = score.getCaliskanScore();
		dto.nandaScore = score.getNandaScore();
		dto.naylorScore = score.getNaylorScore();
		dto.teedeScore = score.getTeedeScore();
		dto.vanLeeuwenScore = score.getVanLeeuwenScore();
		return dto;
	}
}
