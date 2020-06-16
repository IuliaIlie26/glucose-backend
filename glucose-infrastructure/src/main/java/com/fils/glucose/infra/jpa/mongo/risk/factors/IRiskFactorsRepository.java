package com.fils.glucose.infra.jpa.mongo.risk.factors;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.fils.glucose.domain.risk.factors.RiskFactors;

public interface IRiskFactorsRepository extends MongoRepository<RiskFactors, Long> {

}
