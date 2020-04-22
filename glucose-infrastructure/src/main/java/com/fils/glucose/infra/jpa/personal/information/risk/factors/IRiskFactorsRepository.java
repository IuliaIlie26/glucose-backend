package com.fils.glucose.infra.jpa.personal.information.risk.factors;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fils.glucose.domain.medical.info.risk.factors.RiskFactors;

public interface IRiskFactorsRepository extends JpaRepository<RiskFactors, Long> {

}
