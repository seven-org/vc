package com.seven.virtual_currency_website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.seven.virtual_currency_website.data.App;

@SpringBootApplication
@EnableScheduling
@Import(App.class)
public class VirtualCurrencyWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualCurrencyWebsiteApplication.class, args);
	}
	
}
