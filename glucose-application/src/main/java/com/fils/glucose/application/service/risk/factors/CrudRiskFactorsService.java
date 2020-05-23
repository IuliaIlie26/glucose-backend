package com.fils.glucose.application.service.risk.factors;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.fils.glucose.application.exception.TechnicalException;
import com.fils.glucose.domain.personal.information.patient.PatientsRepository;
import com.fils.glucose.domain.risk.factors.RiskFactors;
import com.fils.glucose.domain.risk.factors.RiskFactorsRepository;
import com.fils.glucose.domain.risk.factors.RiskScore;

@Service
public class CrudRiskFactorsService {

	private final RiskFactorsRepository riskFactorsRepository;
	private final PatientsRepository patientRepository;

	public CrudRiskFactorsService(RiskFactorsRepository riskFactorsRepository, PatientsRepository patientRepository) {
		this.riskFactorsRepository = riskFactorsRepository;
		this.patientRepository = patientRepository;
	}

	public RiskFactors findById(Long patientId) {
		return riskFactorsRepository.findById(patientId);
	}

	public RiskScore calculateRiskScore(Long patientId) {
		LocalDate birthDate = this.patientRepository.findBirthdateById(patientId)
				.orElseThrow(() -> new TechnicalException("patient.not.found"));
		LocalDate today = LocalDate.from(LocalDate.now());
		Long age = birthDate.until(today, ChronoUnit.YEARS);

		RiskFactors riskFactors = findById(patientId);
		Double naylor = calculateNaylor(riskFactors, age);
		Double caliskan = calculateCaliskan(riskFactors, age);
		Double vanLeeuwen = calculateVanLeeuwen(riskFactors);
		Double teede = calculateTeede(riskFactors, age);
		Double nanda = calculateNanda(riskFactors, age);
		return new RiskScore(naylor, caliskan, vanLeeuwen, teede, nanda);
	}

	private Double calculateNanda(RiskFactors riskFactors, Long age) {

		int southAsian = 0;
		int eastAsian = 0;
		int previousGdm = 0;
		int macrosomic = 0;

		if (!riskFactors.getRacialOrigin().contains("EAST_ASIAN")) {
			eastAsian = 1;
		}
		if (!riskFactors.getRacialOrigin().contains("SOUTH_ASIAN")) {
			southAsian = 1;
		}

		if (riskFactors.isMacrosomicBaby()) {
			macrosomic = 1;
		}

		if (riskFactors.isPreviousGDM()) {
			previousGdm = 1;
		}

		Double bmi = calculateBmi(riskFactors.getHeight(), riskFactors.getWeight());
		Double b = -8.68947 + (0.05365 * age) + (0.10852 * bmi) + (1.00312 * southAsian) + (0.88785 * eastAsian)
				+ (3.72259 * previousGdm) + (0.67673 * macrosomic);
		return 1 / (1 + Math.exp(-b));
	}

	private Double calculateTeede(RiskFactors riskFactors, Long age) {
		Double score = 0.0;

		if (age >= 25 && age <= 34) {
			score += 1;
		} else if (age >= 35) {
			score += 2;
		}
		Double bmi = calculateBmi(riskFactors.getHeight(), riskFactors.getWeight());
		if (bmi > 20 && bmi <= 34.9) {
			score += 1;
		} else if (bmi >= 35) {
			score += 2;
		}
		if (riskFactors.getRacialOrigin().contains("AFRICAN") || riskFactors.getRacialOrigin().contains("ASIAN")) {
			score += 1;
		}

		if (riskFactors.getFamilyHistoryOfDiabetes().contains("1ST")) {
			score += 1;
		}

		if (riskFactors.isPreviousGDM()) {
			score += 2;
		}
		return score;
	}

	private Double calculateBmi(Integer height, Integer weight) {
		return weight / Math.pow((height / 100), 2);
	}

	private Double calculateVanLeeuwen(RiskFactors riskFactors) {
		int nonCaucasian = 0;
		int familyWithDiabetes = 0;
		int multiparaWithoutGDM = 0;
		int multiparaWithGDM = 0;

		if (riskFactors.isMultipara()) {
			if (riskFactors.isPreviousGDM()) {
				multiparaWithGDM = 1;
			} else {
				multiparaWithoutGDM = 1;
			}
		}

		if (!"None".equalsIgnoreCase(riskFactors.getFamilyHistoryOfDiabetes())) {
			familyWithDiabetes = 1;
		}

		if (!riskFactors.getRacialOrigin().contains("WHITE")) {
			nonCaucasian = 1;
		}

		Double bmi = calculateBmi(riskFactors.getHeight(), riskFactors.getWeight());
		Double b = -6.1 + (0.83 * nonCaucasian) + (0.57 * familyWithDiabetes) - (0.67 * multiparaWithoutGDM)
				+ (0.5 * multiparaWithGDM) + (0.13 * bmi);
		return 1 / (1 + Math.exp(-b));
	}

	private Double calculateCaliskan(RiskFactors riskFactors, Long age) {
		Double score = 0.0;
		if (age > 25) {
			score += 1;
		}

		Double bmi = calculateBmi(riskFactors.getHeight(), riskFactors.getWeight());
		if (bmi > 25) {
			score += 1;
		}

		if (riskFactors.getFamilyHistoryOfDiabetes().contains("1ST")) {
			score += 1;
		}

		if (riskFactors.isMacrosomicBaby()) {
			score += 1;
		}
		if (riskFactors.isHistoryOfAdverseOutcomes()) {
			score += 1;
		}
		return score;
	}

	private Double calculateNaylor(RiskFactors riskFactors, Long age) {
		Double score = 0.0;

		if (age > 30 && age < 35) {
			score += 1;
		} else if (age > 35) {
			score += 2;
		}

		Double bmi = calculateBmi(riskFactors.getHeight(), riskFactors.getWeight());
		if (bmi > 22 && bmi <= 25) {
			score += 2;
		} else if (bmi > 25) {
			score += 3;
		}

		if (riskFactors.getRacialOrigin().equalsIgnoreCase("EAST_ASIAN")) {
			score += 5;
		} else if (riskFactors.getRacialOrigin().equalsIgnoreCase("SOUTH_ASIAN")) {
			score += 2;
		}

		return score;
	}

}
