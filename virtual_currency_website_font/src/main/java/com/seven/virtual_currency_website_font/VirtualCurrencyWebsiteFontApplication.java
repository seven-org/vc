package com.seven.virtual_currency_website_font;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.seven.virtual_currency_website_data.VirtualCurrencyWebsiteDataApplication;

@SpringBootApplication
@Import(VirtualCurrencyWebsiteDataApplication.class)
public class VirtualCurrencyWebsiteFontApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualCurrencyWebsiteFontApplication.class, args);
	}
	
}
