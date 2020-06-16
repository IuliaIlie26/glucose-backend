package com.fils.glucose.exposition.mappers;

import org.springframework.stereotype.Service;

import com.fils.glucose.domain.risk.factors.RiskFactors;
import com.fils.glucose.exposition.dto.RiskFactorsDto;

@Service
public class RiskFactorsMapperService {

	public RiskFactorsDto mapFromDomain(RiskFactors risk) {
		RiskFactorsDto dto = new RiskFactorsDto();
		dto.conceptionMethod = risk.getConceptionMethod();
		dto.familyHistoryOfDiabetes = risk.getFamilyHistoryOfDiabetes();
		dto.height = risk.getHeight();
		dto.macrosomicBaby = risk.isMacrosomicBaby();
		dto.patientId = risk.getPatientId();
		dto.previousGDM = risk.isPreviousGDM();
		dto.racialOrigin = risk.getRacialOrigin();
		dto.smoker = risk.isSmoker();
		dto.weight = risk.getWeight();
		return dto;
	}
	
	public RiskFactors mapRiskFactorsToDomain(RiskFactorsDto dto) {
		return new RiskFactors(dto.patientId, dto.height, dto.weight, dto.racialOrigin, dto.conceptionMethod,
				dto.familyHistoryOfDiabetes, dto.smoker, dto.macrosomicBaby, dto.previousGDM,
				dto.historyOfAdverseOutcomes, dto.multipara);
	}
}
