package com.fils.glucose.application.service.patient;

import org.springframework.stereotype.Service;

import com.fils.glucose.application.exception.TechnicalException;
import com.fils.glucose.application.password.AESEncryptionService;
import com.fils.glucose.application.password.EmailService;
import com.fils.glucose.application.password.PasswordGeneratorService;
import com.fils.glucose.domain.personal.information.patient.Patient;
import com.fils.glucose.domain.personal.information.patient.PatientsRepository;
import com.fils.glucose.domain.users.UserRoles;
import com.fils.glucose.domain.users.Users;
import com.fils.glucose.domain.users.UsersRepository;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class CrudPatientService {

	private final PatientsRepository patientRepository;
	private final UsersRepository usersRepository;
	private final PasswordGeneratorService passGeneratorService;
	private final AESEncryptionService aesEncryptionService;
	private final EmailService emailService;

	public CrudPatientService(PatientsRepository patientRepository, UsersRepository usersRepository,
			PasswordGeneratorService passGeneratorService, AESEncryptionService aesEncryptionService,
			EmailService emailService) {
		this.patientRepository = requireNonNull(patientRepository);
		this.aesEncryptionService = aesEncryptionService;
		this.emailService = emailService;
		this.usersRepository = usersRepository;
		this.passGeneratorService = passGeneratorService;

	}

	public Long savePatient(Patient patient) {
		if (patientRepository.findIdByCnp(patient.getCnp()).isPresent()) {
			throw new TechnicalException("backend.patient.cnp.exists");
		} else if (patientRepository.findByEmail(patient.getEmail()).isPresent()) {
			throw new TechnicalException("backend.patient.email.exists");
		}

		Patient patientFromDb = patientRepository.save(patient);

		String password = passGeneratorService.generatePassword();
		Users newUser = new Users(patient.getEmail(), aesEncryptionService.encrypt(password), UserRoles.PATIENT);
		usersRepository.save(newUser);
		
		emailService.sendEmail(patientFromDb.getEmail(), password);

		return patientFromDb.getId();
	}

	public void deletePatientById(Long idLongValue) {
		patientRepository.deleteById(idLongValue);

	}

	public void updatePatient(Patient patient) {
		patientRepository.save(patient);
	}

	public String getFullFormatAgeById(Long id) {
		LocalDate birthDate = this.patientRepository.findBirthdateById(id)
				.orElseThrow(() -> new TechnicalException("backend.patient.not.found"));
		LocalDate today = LocalDate.from(LocalDate.now());
		Long months = birthDate.until(today, ChronoUnit.MONTHS);
		return months / 12 + " years, " + months % 12 + " months";
	}

	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	public Patient getPatientById(Long id) {
		return patientRepository.findById(id).orElseThrow(() -> new TechnicalException("backend.patient.not.found"));
	}

	public Optional<Long> findIdByCnp(String cnp) {
		return patientRepository.findIdByCnp(cnp);
	}

	public Patient findByCnp(String cnp) {
		return patientRepository.findByCnp(cnp).orElseThrow(() -> new TechnicalException("backend.patient.not.found"));
	}

	public Optional<Patient> getPatientByEmail(String username) {
		return patientRepository.findByEmail(username);
	}
}