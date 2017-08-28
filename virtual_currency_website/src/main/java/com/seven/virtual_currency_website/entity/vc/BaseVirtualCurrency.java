package com.seven.virtual_currency_website.entity.vc;

import java.io.Serializable;

public class BaseVirtualCurrency implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BaseVirtualCurrency() {
		super();
	}

	private String id;
	
	private String name;
	
	private String currentPrice;
	
	private String source;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(String currentPrice) {
		this.currentPrice = currentPrice;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	
}
