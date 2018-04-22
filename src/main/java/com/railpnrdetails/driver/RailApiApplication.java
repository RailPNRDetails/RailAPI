package com.railpnrdetails.driver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.railpnrdetails.driver")
public class RailApiApplication {

	public static void main(String[] args) {
		System.out.println("new change");
		SpringApplication.run(RailApiApplication.class, args);
	}
}
