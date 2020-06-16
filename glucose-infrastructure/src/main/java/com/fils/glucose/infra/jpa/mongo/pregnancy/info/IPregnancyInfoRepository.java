package com.fils.glucose.infra.jpa.mongo.pregnancy.info;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fils.glucose.domain.pregnancy.info.PregnancyInfo;

public interface IPregnancyInfoRepository extends MongoRepository<PregnancyInfo, Long> {

}
