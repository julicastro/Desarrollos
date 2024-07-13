package com.challenge.app.challenge;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}

	/*
	@Bean
	public CommandLineRunner createPasswordsCommand(PasswordEncoder passwordEncoder){
		return args -> {
			System.out.println(passwordEncoder.encode("12345"));
		};
	}
	*/

}
