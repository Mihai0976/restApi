package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ScpRestApplication {
	public static void main(String[] args) {
		SpringApplication.run(ScpRestApplication.class, args);
	}
	/*@Autowired SCPentityRepository repository;

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
	}*/

}


