package com.fils.glucose.application.service.pregnancy.info;

import org.springframework.stereotype.Service;

import com.fils.glucose.domain.pregnancy.info.PregnancyInfo;
import com.fils.glucose.domain.pregnancy.info.PregnancyInfoRepository;

@Service
public class PregnancyInfoService {

	private final PregnancyInfoRepository pregnancyRepo;

	public PregnancyInfoService(PregnancyInfoRepository pregnancyRepo) {
		this.pregnancyRepo = pregnancyRepo;
	}

	public PregnancyInfo getPregancyInfoByPatientId(Long patientId) {
		return pregnancyRepo.findByPatientId(patientId).orElse(new PregnancyInfo());
	}

	public void save(PregnancyInfo pregnancyInfo) {
		pregnancyRepo.save(pregnancyInfo);
	}
}
