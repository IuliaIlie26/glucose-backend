package com.fils.glucose.application.service.doctor;

import org.springframework.stereotype.Service;

import com.fils.glucose.application.exception.TechnicalException;
import com.fils.glucose.application.password.AESEncryptionService;
import com.fils.glucose.application.password.EmailService;
import com.fils.glucose.application.password.PasswordGeneratorService;
import com.fils.glucose.domain.personal.information.doctor.Doctor;
import com.fils.glucose.domain.personal.information.doctor.DoctorRepository;
import com.fils.glucose.domain.users.UserRoles;
import com.fils.glucose.domain.users.Users;
import com.fils.glucose.domain.users.UsersRepository;

import static java.util.Objects.requireNonNull;
import java.util.List;

@Service
public class CrudDoctorService {

	private final DoctorRepository doctorRepository;
	private final UsersRepository usersRepository;
	private final PasswordGeneratorService passGeneratorService;
	private final AESEncryptionService aesEncryptionService;
	private final EmailService emailService;

	public CrudDoctorService(DoctorRepository doctorRepository, UsersRepository usersRepository,
			PasswordGeneratorService passGeneratorService, AESEncryptionService aesEncryptionService,
			EmailService emailService) {
		this.doctorRepository = requireNonNull(doctorRepository);
		this.usersRepository = usersRepository;
		this.emailService = emailService;
		this.aesEncryptionService = aesEncryptionService;
		this.passGeneratorService = passGeneratorService;
	}

	public Doctor findDoctorById(Long doctorId) {
		return doctorRepository.findById(doctorId)
				.orElseThrow(() -> new TechnicalException("backend.doctor.not.found"));
	}

	public List<Doctor> findAll() {
		return doctorRepository.findAll();
	}

	public void save(Doctor doc) {
		doctorRepository.save(doc);
		String password = passGeneratorService.generatePassword();
		usersRepository.save(new Users(doc.getEmail(), aesEncryptionService.encrypt(password), UserRoles.DOCTOR));
		emailService.sendEmail(doc.getEmail(), password);

	}

	public List<Doctor> getDoctorsBySpeciality(String speciality) {
		return doctorRepository.getDoctorsBySpeciality(speciality);
	}

	public Long getDoctorIdByUsername(String username) {
		return doctorRepository.getDoctorIdByEmail(username).map(Doctor::getId)
				.orElseThrow(() -> new TechnicalException("backend.doctor.not.found"));
	}

	public String getDoctorNameAndLastname(Long id) {
		Doctor doctor = doctorRepository.findById(id)
				.orElseThrow(() -> new TechnicalException("backend.doctor.not.found"));
		return doctor.getFirstName() + " " + doctor.getLastName();
	}

	public void deleteById(Long doctorId) {
		doctorRepository.deleteById(doctorId);
		
	}
}
