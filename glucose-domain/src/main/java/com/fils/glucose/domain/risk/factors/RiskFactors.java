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

	public RiskFactors(Long patientId, Integer height, Integer weight, String racialOrigin, String conception,
			String familyHistoryOfDiabetes, boolean smoker, boolean macrosomicBaby, boolean previousGDM) {
		this.patientId = patientId;
		this.height = height;
		this.weight = weight;
		this.racialOrigin = racialOrigin;
		this.conceptionMethod = conception;
		this.familyHistoryOfDiabetes = familyHistoryOfDiabetes;
		this.smoker = smoker;
		this.macrosomicBaby = macrosomicBaby;
		this.previousGDM = previousGDM;
	}
}
