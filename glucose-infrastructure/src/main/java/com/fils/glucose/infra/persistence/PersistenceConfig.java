package com.fils.glucose.infra.persistence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories("com.fils.glucose")
@EntityScan(basePackages = { "com.fils.glucose", "org.springframework.data.jpa.convert.threeten" })
@ComponentScan(basePackages = { "com.fils.glucose" })
public class PersistenceConfig {
}
