package com.railpnrdetails.driver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.railpnrdetails.driver")
public class RailApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RailApiApplication.class, args);
	}
}