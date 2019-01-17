package com.sainsburys.integration;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
public class ApplicationInitializer {
	private static Logger log = LogManager.getLogger(ApplicationInitializer.class);
	public static void main(String[] args) {
		log.info("Initializing Spring Boot Application");
		SpringApplication.run(ApplicationInitializer.class, args);
		}
	
}
