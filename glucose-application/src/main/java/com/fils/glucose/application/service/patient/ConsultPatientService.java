package com.fils.glucose.application.service.patient;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.stereotype.Service;

import com.fils.glucose.application.exception.TechnicalException;
import com.fils.glucose.domain.personal.information.patient.Patient;
import com.fils.glucose.domain.personal.information.patient.PatientsRepository;

import static java.util.Objects.requireNonNull;

@Service
public class ConsultPatientService {

	private final PatientsRepository patientsRepository;

	public ConsultPatientService(PatientsRepository patientsRepository) {
		this.patientsRepository = requireNonNull(patientsRepository);
	}

	public String getFullFormatAgeById(Long id) {
		LocalDate birthDate = this.patientsRepository.findBirthdateById(id)
				.orElseThrow(() -> new TechnicalException("patient.not.found"));
		LocalDate today = LocalDate.from(LocalDate.now());
		Long months = birthDate.until(today, ChronoUnit.MONTHS);
		return months / 12 + " years, " + months % 12 + " months";
	}

	public List<Patient> getAllPatients() {
		return patientsRepository.findAll();
	}

}
