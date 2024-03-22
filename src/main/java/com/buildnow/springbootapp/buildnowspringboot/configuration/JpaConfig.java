package com.buildnow.springbootapp.buildnowspringboot.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaConfig {
    //JPA auditing 활성화
}
