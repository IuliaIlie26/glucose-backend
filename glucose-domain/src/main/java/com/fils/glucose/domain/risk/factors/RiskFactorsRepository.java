package com.fils.glucose.domain.risk.factors;

import java.util.Optional;

public interface RiskFactorsRepository {
	void save(RiskFactors riskFactors);

	Optional<RiskFactors> findById(Long patientId);
}
