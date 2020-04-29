package com.fils.glucose.application.service.patient;

import org.springframework.stereotype.Service;

import com.fils.glucose.application.exception.TechnicalException;
import com.fils.glucose.domain.medical.info.risk.factors.RiskFactors;
import com.fils.glucose.domain.medical.info.risk.factors.RiskFactorsRepository;
import com.fils.glucose.domain.personal.information.patient.Patient;
import com.fils.glucose.domain.personal.information.patient.PatientsRepository;

import static java.util.Objects.requireNonNull;

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
}
