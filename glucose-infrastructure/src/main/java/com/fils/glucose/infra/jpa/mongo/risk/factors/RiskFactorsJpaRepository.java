package com.fils.glucose.infra.jpa.mongo.risk.factors;

import org.springframework.stereotype.Repository;
import com.fils.glucose.domain.risk.factors.RiskFactors;
import com.fils.glucose.domain.risk.factors.RiskFactorsRepository;
import static java.util.Objects.requireNonNull;

@Repository
public class RiskFactorsJpaRepository implements RiskFactorsRepository {

	private final IRiskFactorsRepository factorsRepository;

	public RiskFactorsJpaRepository(IRiskFactorsRepository factorsRepository) {
		this.factorsRepository = requireNonNull(factorsRepository);
	}

	@Override
	public void save(RiskFactors riskFactors) {
		factorsRepository.save(riskFactors);
	}

	@Override
	public RiskFactors findById(Long patientId) {
		return factorsRepository.findById(patientId).orElse(new RiskFactors());
	}
}
