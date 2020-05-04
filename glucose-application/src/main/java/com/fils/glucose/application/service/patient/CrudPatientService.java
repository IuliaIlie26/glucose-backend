package com.fils.glucose.application.service.patient;

import org.springframework.stereotype.Service;

import com.fils.glucose.application.exception.TechnicalException;
import com.fils.glucose.domain.medical.info.risk.factors.RiskFactors;
import com.fils.glucose.domain.medical.info.risk.factors.RiskFactorsRepository;
import com.fils.glucose.domain.personal.information.patient.Patient;
import com.fils.glucose.domain.personal.information.patient.PatientsRepository;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class CrudPatientService {

	private final PatientsRepository patientRepository;
	private final RiskFactorsRepository riskFactorsRepository;

	public CrudPatientService(PatientsRepository patientRepository, RiskFactorsRepository riskFactorsRepository) {
		this.patientRepository = requireNonNull(patientRepository);
		this.riskFactorsRepository = requireNonNull(riskFactorsRepository);

	}

	public Long savePatient(Patient patient) {
		if (patientRepository.findByCnp(patient.getCnp()).isPresent()) {
			throw new TechnicalException("patient.cnp.exists");
		} else if (patientRepository.findByEmail(patient.getEmail()).isPresent()) {
			throw new TechnicalException("patient.email.exists");
		}
		return patientRepository.save(patient).getId();
	}

	public void saveRiskFactors(RiskFactors riskFactors) {
		riskFactorsRepository.save(riskFactors);
	}

	public void deletePatientById(Long idLongValue) {
		patientRepository.deleteById(idLongValue);

	}

	public void updatePatient(Patient patient) {
		patientRepository.save(patient);
	}
	
	public String getFullFormatAgeById(Long id) {
		LocalDate birthDate = this.patientRepository.findBirthdateById(id)
				.orElseThrow(() -> new TechnicalException("patient.not.found"));
		LocalDate today = LocalDate.from(LocalDate.now());
		Long months = birthDate.until(today, ChronoUnit.MONTHS);
		return months / 12 + " years, " + months % 12 + " months";
	}

	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	public Patient getPatientById(Long id) {
		return patientRepository.findById(id).orElseThrow(() -> new TechnicalException("patient.not.found"));
	}

	public Optional<Long> findByCnp(String cnp) {
		return patientRepository.findByCnp(cnp);
	}
}