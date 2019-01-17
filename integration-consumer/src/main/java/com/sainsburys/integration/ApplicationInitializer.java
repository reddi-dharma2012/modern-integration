package com.sainsburys.integration;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationInitializer {
	private static final Logger log = LoggerFactory.getLogger(ApplicationInitializer.class);
	
	public static void main(String[] args) {
		log.info("Initializing Spring Boot Application");
		SpringApplication.run(ApplicationInitializer.class, args);
		}
	
}
