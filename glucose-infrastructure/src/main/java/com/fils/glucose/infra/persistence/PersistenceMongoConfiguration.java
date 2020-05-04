package com.fils.glucose.infra.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import com.mongodb.MongoClientURI;

@PropertySource({ "classpath:application.properties" })
@Configuration
public class PersistenceMongoConfiguration {

	@Autowired
	private Environment env;
	
	public PersistenceMongoConfiguration() {
		super();
	}

	@Bean
	public MongoDbFactory mongoDbFactory() {
		String property = env.getProperty("spring.data.mongodb.uri");
		return new SimpleMongoDbFactory(new MongoClientURI(property));
	}

	@Bean(name = "mongoTemplate")
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoDbFactory());
	}
}
