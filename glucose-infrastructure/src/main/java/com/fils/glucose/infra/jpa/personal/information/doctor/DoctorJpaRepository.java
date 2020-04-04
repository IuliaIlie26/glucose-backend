package com.fils.glucose.infra.jpa.personal.information.doctor;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import com.fils.glucose.domain.personal.information.doctor.Doctor;
import com.fils.glucose.domain.personal.information.doctor.DoctorRepository;
import com.fils.glucose.infra.jpa.users.IUsersRepository;
import static java.util.Objects.requireNonNull;

@Primary
@Repository
public class DoctorJpaRepository implements DoctorRepository {

	private final IDoctorJpaRepository doctorRepository;
	private final IUsersRepository usersRepository;

	public DoctorJpaRepository(IDoctorJpaRepository doctorJpaRepository, IUsersRepository usersRepository) {
		this.doctorRepository = requireNonNull(doctorJpaRepository);
		this.usersRepository = requireNonNull(usersRepository);
	}

	@Override
	public Optional<Doctor> findById(String doctorId) {
		return doctorRepository.findById(doctorId);
	}

	@Override
	public Optional<String> findUserIdByUsername(String userId) {
		return usersRepository.findUsernameByUserId(userId);
	}
}
