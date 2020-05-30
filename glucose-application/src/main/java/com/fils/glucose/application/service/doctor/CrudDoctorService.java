package com.fils.glucose.application.service.doctor;

import org.springframework.stereotype.Service;
import com.fils.glucose.application.exception.TechnicalException;
import com.fils.glucose.domain.personal.information.doctor.Doctor;
import com.fils.glucose.domain.personal.information.doctor.DoctorRepository;
import com.fils.glucose.domain.users.Users;
import com.fils.glucose.domain.users.UsersRepository;

import static java.util.Objects.requireNonNull;
import java.util.List;

@Service
public class CrudDoctorService {

	private final DoctorRepository doctorRepository;
	private final UsersRepository usersRepository;

	public CrudDoctorService(DoctorRepository doctorRepository, UsersRepository usersRepository) {
		this.doctorRepository = requireNonNull(doctorRepository);
		this.usersRepository = usersRepository;
	}

	public Doctor findDoctorById(Long doctorId) {
		return doctorRepository.findById(doctorId).orElseThrow(() -> new TechnicalException("doctor.not.found"));
	}

	public List<Doctor> findAll() {
		return doctorRepository.findAll();
	}

	public void save(Doctor doc) {
		doctorRepository.save(doc);
		usersRepository.save(new Users(doc.getEmail(), "test", "DOCTOR"));
		// TODO trimite credentialele pe mailul doctorului
		// TODO genereaza parola

	}

	public List<Doctor> getDoctorsBySpeciality(String speciality) {
		return doctorRepository.getDoctorsBySpeciality(speciality);
	}

	public Long getDoctorIdByUsername(String username) {
		return doctorRepository.getDoctorIdByEmail(username).map(Doctor::getId)
				.orElseThrow(() -> new TechnicalException("doctor.not.found"));
	}

	public String getDoctorNameAndLastname(Long id) {
		Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new TechnicalException("doctor.not.found"));
		return doctor.getFirstName() + " " + doctor.getLastName();
	}
}
