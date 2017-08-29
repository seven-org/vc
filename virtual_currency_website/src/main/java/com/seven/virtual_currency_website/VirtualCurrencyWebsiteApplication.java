package com.seven.virtual_currency_website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VirtualCurrencyWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualCurrencyWebsiteApplication.class, args);
	}
}
