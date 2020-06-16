package com.fils.glucose.domain.risk.factors;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "risk_factors")
public class RiskFactors {

	@Id
	@NotNull
	private Long patientId;
	@NotNull
	private Integer height;
	@NotNull
	private Integer weight;
	@NotNull
	private String racialOrigin;
	@NotNull
	private String conceptionMethod;
	@NotNull
	private String familyHistoryOfDiabetes;
	@NotNull
	private boolean smoker;
	@NotNull
	private boolean macrosomicBaby;
	@NotNull
	private boolean previousGDM;

	@NotNull
	private boolean historyOfAdverseOutcomes;
	@NotNull
	private boolean multipara;

	public RiskFactors() {

	}

	public RiskFactors(Long patientId, Integer height, Integer weight, String racialOrigin, String conception,
			String familyHistoryOfDiabetes, boolean smoker, boolean macrosomicBaby, boolean previousGDM,
			boolean historyOfAdverseOutcomes, boolean multipara) {
		this.patientId = patientId;
		this.height = height;
		this.weight = weight;
		this.racialOrigin = racialOrigin;
		this.conceptionMethod = conception;
		this.familyHistoryOfDiabetes = familyHistoryOfDiabetes;
		this.smoker = smoker;
		this.macrosomicBaby = macrosomicBaby;
		this.previousGDM = previousGDM;
		this.historyOfAdverseOutcomes = historyOfAdverseOutcomes;
		this.multipara = multipara;
	}

	public boolean isMultipara() {
		return multipara;
	}

	public Long getPatientId() {
		return patientId;
	}

	public Integer getHeight() {
		return height;
	}

	public Integer getWeight() {
		return weight;
	}

	public String getRacialOrigin() {
		return racialOrigin;
	}

	public String getConceptionMethod() {
		return conceptionMethod;
	}

	public String getFamilyHistoryOfDiabetes() {
		return familyHistoryOfDiabetes;
	}

	public boolean isSmoker() {
		return smoker;
	}

	public boolean isMacrosomicBaby() {
		return macrosomicBaby;
	}

	public boolean isPreviousGDM() {
		return previousGDM;
	}

	public boolean isHistoryOfAdverseOutcomes() {
		return historyOfAdverseOutcomes;
	}
}
