package com.fils.glucose;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class GlucoseApplication{

	public static void main(String[] args) {
		new SpringApplicationBuilder(GlucoseApplication.class).run(args);
	}
	
}