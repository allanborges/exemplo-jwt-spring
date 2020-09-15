package com.everis.cleaner.inventory.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.everis.cleaner.inventory")
public class CleanerInventoryApp {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/cleaner-inventory-api");
		SpringApplication.run(CleanerInventoryApp.class, args);
	}

}
