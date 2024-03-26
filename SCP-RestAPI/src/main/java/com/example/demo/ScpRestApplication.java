package com.example.demo;

import com.example.demo.model.SCPentity;
import com.example.demo.repository.SCPentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ScpRestApplication {
	public static void main(String[] args) {
		SpringApplication.run(ScpRestApplication.class, args);
	}
	@Autowired SCPentityRepository repository;

	@Bean
	CommandLineRunner runner(SCPentityRepository repository) {
		return args -> {
			SCPentity scpEntity = new SCPentity(
					"SCP-002",
					"The Living Room",
					"Euclid",
					"It's a Living Room. A bit odd to be honest"

			);
			repository.insert(scpEntity);
		};
	}

}


