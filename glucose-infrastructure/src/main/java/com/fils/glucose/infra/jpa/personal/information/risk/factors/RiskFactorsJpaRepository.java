package com.fils.glucose.infra.jpa.personal.information.risk.factors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.fils.glucose.domain.medical.info.risk.factors.RiskFactors;
import com.fils.glucose.domain.medical.info.risk.factors.RiskFactorsRepository;
import static java.util.Objects.requireNonNull;

@Primary
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
}
