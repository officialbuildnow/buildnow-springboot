package com.buildnow.springbootapp.buildnowspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@PropertySource("classpath:/application.properties")
@EnableJpaAuditing
public class BuildnowSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuildnowSpringbootApplication.class, args);
	}

}
