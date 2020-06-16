package com.fils.glucose.exposition;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

import com.fils.glucose.application.ApplicationConfig;

@SpringBootApplication
@Import(ApplicationConfig.class)
public class GlucoseApplication{

	public static void main(String[] args) {
		new SpringApplicationBuilder(GlucoseApplication.class).run(args);
	}
}