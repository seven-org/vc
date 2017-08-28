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

@Component
/*
 * 将网站站点与processor匹配，并放入map
 */
public class WebsiteForProcessorComponent {
	
	@Autowired
	public WebsiteURL websiteURL;
	
	private Map<String, AbstractDataProcessor<?>> processorMap;

	public Map<String, AbstractDataProcessor<?>> getProcessorMap() {
		return processorMap;
	}

	public void setProcessorMap(Map<String, AbstractDataProcessor<?>> processorMap) {
		this.processorMap = processorMap;
	}

	public Map<String, AbstractDataProcessor<?>> loadProcessMap() {
		processorMap.clear();
		processorMap.put(websiteURL.getBicAndltc(), new BicProcessor(websiteURL.getBicAndltc()));
		processorMap.put(websiteURL.getEth(), new EthProcessor(websiteURL.getEth()));
		//TODO
		return processorMap;
	}
	
	public WebsiteForProcessorComponent() {
		super();
		processorMap = new HashMap<String, AbstractDataProcessor<?>>();
	}

}
