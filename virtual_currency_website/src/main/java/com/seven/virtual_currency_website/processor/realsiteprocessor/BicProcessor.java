package com.seven.virtual_currency_website.processor.realsiteprocessor;

import java.util.List;

import com.seven.virtual_currency_website.entity.component.DbResult;
import com.seven.virtual_currency_website.entity.vc.BaseVirtualCurrency;
import com.seven.virtual_currency_website.processor.AbstractDataProcessor;

public class BicProcessor extends AbstractDataProcessor<BaseVirtualCurrency>{
	
	public BicProcessor(String urlstr) {
		super(urlstr);
	}
	
	@Override
	public List<BaseVirtualCurrency> processReturnObjectList(String hr){
		//TODO
		/*
		 * 比特币网站数据处理方法
		 * 
		 * 返回需要存储的数据对象
		 */
		return null;
	}
	
	@Override
	public DbResult<?> toDB(List<BaseVirtualCurrency> datas){
		/*
		 * TODO
		 * 定义持久化方法(mysql)
		 */
		return null;
	}

}
