package com.sainsburys.adaptor;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationInitializer {
	private static final Logger LOG = LoggerFactory.getLogger(ApplicationInitializer.class);
	public static void main(String[] args) {
		LOG.info("Initializing Spring Boot Application");
		SpringApplication.run(ApplicationInitializer.class, args);
		}
	
}
