package com.baykov.daniel.lang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.baykov.daniel")
@EntityScan(basePackages = "com.baykov.daniel")
@EnableJpaRepositories(basePackages = "com.baykov.daniel")
@EnableJpaAuditing
@ConfigurationPropertiesScan(basePackages = "com.baykov.daniel")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
