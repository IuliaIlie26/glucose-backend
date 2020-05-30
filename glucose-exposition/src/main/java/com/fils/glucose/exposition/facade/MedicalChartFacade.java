package com.fils.glucose.exposition.facade;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.fils.glucose.application.service.pregnancy.info.PregnancyInfoService;
import com.fils.glucose.application.service.risk.factors.CrudRiskFactorsService;
import com.fils.glucose.domain.pregnancy.info.PregnancyInfo;
import com.fils.glucose.domain.risk.factors.RiskFactors;
import com.fils.glucose.domain.risk.factors.RiskScore;
import com.fils.glucose.exposition.dto.PregnancyInfoDto;
import com.fils.glucose.exposition.dto.RiskFactorsDto;
import com.fils.glucose.exposition.dto.RiskScoreDto;
import com.fils.glucose.exposition.mappers.RiskFactorsMapperService;

@Service
public class MedicalChartFacade {
	private final CrudRiskFactorsService riskFactorsService;
	private final RiskFactorsMapperService riskFactorMapper;
	private final PregnancyInfoService pregnancyInfoService;
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public MedicalChartFacade(CrudRiskFactorsService riskFactorsService, RiskFactorsMapperService riskFactorMapper,
			PregnancyInfoService pregnancyInfoService) {
		this.riskFactorsService = requireNonNull(riskFactorsService);
		this.riskFactorMapper = requireNonNull(riskFactorMapper);
		this.pregnancyInfoService = requireNonNull(pregnancyInfoService);
	}

	public RiskFactorsDto getRiskFactors(Long patientId) {
		RiskFactors risks = riskFactorsService.findById(patientId);
		return riskFactorMapper.mapFromDomain(risks);
	}

	public PregnancyInfoDto getPregancyInfo(Long patientId) {
		PregnancyInfoDto dto = new PregnancyInfoDto();
		PregnancyInfo pregnancyInfo = pregnancyInfoService.getPregancyInfoByPatientId(patientId);

		dto.dueDate = pregnancyInfo.getDueDate().format(formatter);
		dto.patientId = patientId;
		return dto;
	}

	public void savePregancyInfo(PregnancyInfoDto dto) {
		PregnancyInfo pregnancyInfo = new PregnancyInfo(dto.patientId, LocalDate.parse(dto.dueDate, formatter));
		pregnancyInfoService.save(pregnancyInfo);
	}

	public RiskScoreDto calculateRiskScore(Long patientId) {

		RiskScore score = riskFactorsService.calculateRiskScore(patientId);
		RiskScoreDto dto = new RiskScoreDto();
		dto.caliskanScore = score.getCaliskanScore();
		dto.nandaScore = score.getNandaScore();
		dto.naylorScore = score.getNaylorScore();
		dto.teedeScore = score.getTeedeScore();
		dto.vanLeeuwenScore = score.getVanLeeuwenScore();
		return dto;
	}

	public void saveRiskFactors(RiskFactorsDto riskFactorsDto) {
		RiskFactors riskFactors = riskFactorMapper.mapRiskFactorsToDomain(riskFactorsDto);
		riskFactorsService.saveRiskFactors(riskFactors);
	}

}
