package com.seven.virtual_currency_website.domain;

//@Entity
public class JubiJsonObject {

	public String time;
	
	public double price;
	
	public double count;
	
	public String operation;
	
	public String day;

	public String source = "jubi";
	
	public JubiJsonObject() {
		super();
	}

	public JubiJsonObject(String time, double price, double count, String operation, String day) {
		super();
		this.time = time;
		this.price = price;
		this.count = count;
		this.operation = operation;
		this.day = day;
	}

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

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
}
