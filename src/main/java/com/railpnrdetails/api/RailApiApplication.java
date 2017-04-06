package com.railpnrdetails.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.railpnrdetails.api")
public class RailApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RailApiApplication.class, args);
	}
}
