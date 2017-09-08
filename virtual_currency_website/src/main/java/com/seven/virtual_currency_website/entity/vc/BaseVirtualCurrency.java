package com.seven.virtual_currency_website.entity.vc;

import java.io.Serializable;

/*
 * for redis
 */
public class BaseVirtualCurrency implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BaseVirtualCurrency() {
		super();
	}

	private String id;
	
	//名称
	private String name;
	
	//单价
	private String currentPrice;
	
	//数量
	private String count;
	
	//来源
	private String source;
	
	//时间
	private String time;
	
	//单价double
	private double price;
	
	//数量double
	private double doubleCount;
	
	//操作类型
	private String operation;
	
	//日期
	private String day;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

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

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public double getDoubleCount() {
		return doubleCount;
	}

	public void setDoubleCount(double doubleCount) {
		this.doubleCount = doubleCount;
	}

	
}
