package com.seven.virtual_currency_website.processor.realsiteprocessor;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.seven.virtual_currency_website.dao.mysql.VirtualCurrencyMysqlDao;
import com.seven.virtual_currency_website.domain.VirtualCurrency;
import com.seven.virtual_currency_website.entity.vc.BaseVirtualCurrency;
import com.seven.virtual_currency_website.processor.BaseMysqlDataProcessor;

@Component
public class EthProcessor extends BaseMysqlDataProcessor<VirtualCurrency, VirtualCurrencyMysqlDao>{
	
	@Autowired
	private VirtualCurrencyMysqlDao virtualCurrencyMysqlDao;
	
	@Override
	public List<BaseVirtualCurrency> process(String hr){
		this.domainClass = VirtualCurrency.class;
		//TODO
		/*
		 * 以太坊数据处理方法
		 */
		List<BaseVirtualCurrency> bvcs = new ArrayList<BaseVirtualCurrency>();
		try {
			JSONObject obj = new JSONObject(hr);
			
			String name1 = obj.getJSONObject("ticker").getString("Symbol").toLowerCase();
			String price1 = String.valueOf(obj.getJSONObject("ticker").getDouble("Open"));
			BaseVirtualCurrency bvc1 = new BaseVirtualCurrency();
			bvc1.setCurrentPrice(price1);
			bvc1.setName(name1);
			
			bvcs.add(bvc1);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bvcs;
	}

	@Override
	public List<VirtualCurrency> saveToMysql(List<VirtualCurrency> list) {
		return virtualCurrencyMysqlDao.save(list);
	}

}
