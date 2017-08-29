package com.seven.virtual_currency_website.processor;

import com.seven.virtual_currency_website.entity.component.DbResult;

/*
 * 1.处理获取到的网站数据，
 * 
 * 2.存入DB
 */
public interface DefaultDataProcessor <T> {
	
	public void doDataProcess(String str);
	
	@Deprecated
	public T process(String hr);

	//存入DB
	public DbResult<?> toDB(T datas);
	
}
