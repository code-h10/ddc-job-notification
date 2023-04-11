package com.binary.ddcjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DdcjobApplication {
	public static void main(String[] args) {
		SpringApplication.run(DdcjobApplication.class, args);
	}

}
