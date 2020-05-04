package com.fils.glucose.infra.persistence;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.fils.glucose.infra.jpa.sensors",
        mongoTemplateRef = "mongoTemplate")
public class MongoConfig {

}
