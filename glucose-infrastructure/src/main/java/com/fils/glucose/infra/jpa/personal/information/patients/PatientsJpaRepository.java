package com.fils.glucose.infra.jpa.personal.information.patients;

import org.springframework.stereotype.Repository;
import com.fils.glucose.domain.personal.information.patient.Patient;
import com.fils.glucose.domain.personal.information.patient.PatientsRepository;
import static java.util.Objects.requireNonNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class PatientsJpaRepository implements PatientsRepository {

	private final IPatientJpaRepository patientsRepository;

	public PatientsJpaRepository(IPatientJpaRepository patientsRepository) {
		this.patientsRepository = requireNonNull(patientsRepository);
	}

	public Patient save(Patient patient) {
		return patientsRepository.save(patient);
	}

	public Optional<Patient> findById(Long id) {
		return patientsRepository.findById(id);
	}

	@Override
	public Optional<LocalDate> findBirthdateById(Long id) {
		return patientsRepository.findBirthdateById(id);
	}

	@Override
	public Optional<Long> findByCnp(String cnp) {
		return patientsRepository.findByCnp(cnp);
	}

	@Override
	public Optional<Long> findByEmail(String email) {
		return patientsRepository.findByEmail(email);
	}

	@Override
	public List<Patient> findAll() {
		return patientsRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		patientsRepository.deleteById(id);
	}
}
