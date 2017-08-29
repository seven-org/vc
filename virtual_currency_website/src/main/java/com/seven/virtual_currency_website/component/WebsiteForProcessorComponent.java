package com.seven.virtual_currency_website.component;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.seven.virtual_currency_website.entity.vc_website.BaseVirtualCurrencyWebsite;
import com.seven.virtual_currency_website.entity.vc_website.WebsiteURL;
import com.seven.virtual_currency_website.processor.AbstractDataProcessor;
import com.seven.virtual_currency_website.processor.realsiteprocessor.BicProcessor;
import com.seven.virtual_currency_website.processor.realsiteprocessor.EthProcessor;

/*
 * 将网站站点与processor匹配，并放入map
 */
@Component
public class WebsiteForProcessorComponent {
	
	@Autowired
	public WebsiteURL websiteURL;
	
	private Map<String, Class<?>> processorMap;

	public Map<String, Class<?>> getProcessorMap() {
		return processorMap;
	}

	public void setProcessorMap(Map<String, Class<?>> processorMap) {
		this.processorMap = processorMap;
	}

	public Map<String, Class<?>> loadProcessMap() {
		processorMap.clear();
		processorMap.put(websiteURL.getBicAndltc(), BicProcessor.class);
		processorMap.put(websiteURL.getEth(), EthProcessor.class);
		//TODO
		return processorMap;
	}
	
	public WebsiteForProcessorComponent() {
		super();
		processorMap = new HashMap<String, Class<?>>();
	}

}
