package com.fils.glucose.exposition.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fils.glucose.exposition.dto.PregnancyInfoDto;
import com.fils.glucose.exposition.dto.RiskFactorsDto;
import com.fils.glucose.exposition.dto.RiskScoreDto;
import com.fils.glucose.exposition.facade.MedicalChartFacade;

@RestController
@RequestMapping("api/medical-chart")
@CrossOrigin(origins = "http://localhost:2020")
public class MedicalChartController {

	private final MedicalChartFacade medicalChartFacade;

	public MedicalChartController(MedicalChartFacade medicalChartFacade) {
		this.medicalChartFacade = medicalChartFacade;
	}

	@GetMapping("getRiskFactors")
	public RiskFactorsDto getRiskFactors(@RequestParam Long patientId) {
		return medicalChartFacade.getRiskFactors(patientId);

	}

	@GetMapping("getPregancyInfo")
	public PregnancyInfoDto getPregancyInfo(@RequestParam Long patientId) {
		return medicalChartFacade.getPregancyInfo(patientId);
	}

	@PostMapping("savePregancyInfo")
	public void savePregancyInfo(@RequestBody PregnancyInfoDto dto) {
		medicalChartFacade.savePregancyInfo(dto);
	}

	@GetMapping("calculateRiskScore")
	public RiskScoreDto calculateRiskScore(@RequestParam Long patientId) {
		return medicalChartFacade.calculateRiskScore(patientId);
	}

	@PostMapping("saveRiskFactors")
	public void saveRiskFactors(@RequestBody RiskFactorsDto riskFactors) {
		medicalChartFacade.saveRiskFactors(riskFactors);
	}
}
