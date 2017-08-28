package com.seven.virtual_currency_website.entity.component;

public class DbResult<T> {
	
	private T jpaSaveResult;

	public T getJpaSaveResult() {
		return jpaSaveResult;
	}

	public void setJpaSaveResult(T jpaSaveResult) {
		this.jpaSaveResult = jpaSaveResult;
	}

}
