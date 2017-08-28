package com.seven.virtual_currency_website.entity.vc_website;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="website.virtualCurrency")
public class WebsiteURL {
	
	private String bicAndltc;
	
	private String eth;
	
	public String getBicAndltc() {
		return bicAndltc;
	}

	public void setBicAndltc(String bicAndltc) {
		this.bicAndltc = bicAndltc;
	}

	public String getEth() {
		return eth;
	}

	public void setEth(String eth) {
		this.eth = eth;
	}

	List<String> virtualCurrency = new ArrayList<String>();

	public List<String> getVirtualCurrency() {
		
		virtualCurrency.add(eth);
		virtualCurrency.add(bicAndltc);
		
		return virtualCurrency;
	}

	public void setVirtualCurrency(List<String> virtualCurrency) {
		this.virtualCurrency = virtualCurrency;
	}

}
