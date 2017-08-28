package com.seven.virtual_currency_website.processor.realsiteprocessor;

import java.util.List;

import com.seven.virtual_currency_website.entity.vc.BaseVirtualCurrency;
import com.seven.virtual_currency_website.processor.AbstractDataProcessor;

public class EthProcessor extends AbstractDataProcessor<BaseVirtualCurrency>{
	
	public EthProcessor(String urlstr) {
		super(urlstr);
	}
	
	@Override
	public List<BaseVirtualCurrency> processReturnObjectList(String hr){
		//TODO
		/*
		 * 以太坊数据处理方法
		 */
		return null;
	}

}
