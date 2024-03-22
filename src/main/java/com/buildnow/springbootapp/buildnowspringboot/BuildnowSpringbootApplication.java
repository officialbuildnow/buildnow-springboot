package com.buildnow.springbootapp.buildnowspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:/application.properties")
public class BuildnowSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuildnowSpringbootApplication.class, args);
	}

}
