package com.seven.virtual_currency_website.processor;

import com.seven.virtual_currency_website.entity.component.DbResult;

/*
 * 数据处理接口adapter
 * 
 * 每个url都有不同的数据处理方法
 */
public abstract class AbstractDataProcessor <T> implements DefaultDataProcessor <T> {
	
	private String urlstr;
	
	public String getUrlstr() {
		return urlstr;
	}

	public void setUrlstr(String urlstr) {
		this.urlstr = urlstr;
	}

	public AbstractDataProcessor() {
		super();
	}
	
	public AbstractDataProcessor(String urlstr) {
		super();
		this.urlstr = urlstr;
	}

	@Override
	public void doDataProcess(String str){
	}
	
	@Override
	@Deprecated
	public T process(String hr){
		return null;
	}
	
	@Override
	public DbResult<?> toDB(T datas){
		return null;
	}

}
