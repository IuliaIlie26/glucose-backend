package com.fils.glucose.infra.jpa.mongo.pregnancy.info;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.fils.glucose.domain.pregnancy.info.PregnancyInfo;
import com.fils.glucose.domain.pregnancy.info.PregnancyInfoRepository;

@Repository
public class PregnancyInfoJpaRepository implements PregnancyInfoRepository {

	private final IPregnancyInfoRepository pregnancyInfoRepository;

	public PregnancyInfoJpaRepository(IPregnancyInfoRepository pregnancyInfoRepository) {
		this.pregnancyInfoRepository = pregnancyInfoRepository;
	}

	@Override
	public void save(PregnancyInfo info) {
		pregnancyInfoRepository.save(info);

	}

	@Override
	public Optional<PregnancyInfo> findByPatientId(Long id) {
		return pregnancyInfoRepository.findById(id);
	}
}
