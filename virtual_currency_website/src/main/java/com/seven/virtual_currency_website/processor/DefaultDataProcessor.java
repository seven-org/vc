package com.seven.virtual_currency_website.processor;

import java.util.List;

import com.seven.virtual_currency_website.entity.component.DbResult;

/*
 * 1.处理获取到的网站数据，
 * 
 * 2.存入DB
 */
public interface DefaultDataProcessor <T> {
	
	public String process(String hr);
	
	public List<T> processReturnObjectList(String hr);

	//存入DB
	public DbResult<?> toDB(List<T> datas);
	
}
