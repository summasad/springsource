package com.example.reservetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ReservetestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservetestApplication.class, args);
	}

}
