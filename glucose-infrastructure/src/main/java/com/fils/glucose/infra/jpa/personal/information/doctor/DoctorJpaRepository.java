package com.fils.glucose.infra.jpa.personal.information.doctor;

import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import com.fils.glucose.domain.personal.information.doctor.Doctor;
import com.fils.glucose.domain.personal.information.doctor.DoctorRepository;
import com.fils.glucose.domain.users.Users;
import com.fils.glucose.infra.jpa.users.IUsersRepository;
import static java.util.Objects.requireNonNull;

import java.util.List;

@Repository
public class DoctorJpaRepository implements DoctorRepository {

	private final IDoctorJpaRepository doctorRepository;
	private final IUsersRepository usersRepository;

	public DoctorJpaRepository(IDoctorJpaRepository doctorJpaRepository, IUsersRepository usersRepository) {
		this.doctorRepository = requireNonNull(doctorJpaRepository);
		this.usersRepository = requireNonNull(usersRepository);
	}

	@Override
	public Optional<Doctor> findById(Long doctorId) {
		return doctorRepository.findById(doctorId);
	}

	@Override
	public Optional<Users> findByUsername(String username) {
		return usersRepository.findByUsername(username);
	}

	@Override
	public List<Doctor> findAll() {
		return doctorRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
	}

	@Override
	public void save(Doctor doc) {
		doctorRepository.save(doc);
	}

	@Override
	public List<Doctor> getDoctorsBySpeciality(String speciality) {
		return doctorRepository.findBySpeciality(speciality);
	}

	@Override
	public Optional<Doctor> getDoctorIdByEmail(String email) {
		return doctorRepository.findByEmail(email);
	}

	@Override
	public void deleteById(Long doctorId) {
		doctorRepository.deleteById(doctorId);

	}
}
