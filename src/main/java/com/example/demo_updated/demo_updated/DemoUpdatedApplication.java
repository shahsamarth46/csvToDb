package com.example.demo_updated.demo_updated;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class DemoUpdatedApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoUpdatedApplication.class, args);
	}

}
