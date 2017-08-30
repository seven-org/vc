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
public class BicProcessor extends BaseMysqlDataProcessor<VirtualCurrency, VirtualCurrencyMysqlDao>{
	
	@Autowired
	private VirtualCurrencyMysqlDao virtualCurrencyMysqlDao;
	
	@Override
	public List<BaseVirtualCurrency> process(String hr){
		//TODO
		/*
		 * 比特币网站数据处理方法
		 * 
		 * 返回需要存储的数据对象
		 */
//		this.toDB(datas);
		List<BaseVirtualCurrency> bvcs = new ArrayList<BaseVirtualCurrency>();
		try {
			JSONObject obj = new JSONObject(hr);
			
			String name1 = "btc";
			String price1 = obj.getJSONObject("ticker_btccny").getString("sell");
			BaseVirtualCurrency bvc1 = new BaseVirtualCurrency();
			bvc1.setCurrentPrice(price1);
			bvc1.setName(name1);
			
			String name2 = "ltc";
			String price2 = obj.getJSONObject("ticker_ltccny").getString("sell");
			BaseVirtualCurrency bvc2 = new BaseVirtualCurrency();
			bvc2.setCurrentPrice(price2);
			bvc2.setName(name2);
			
			bvcs.add(bvc1);
			bvcs.add(bvc2);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bvcs;
	}

	@Override
	public void setClass() {
		this.domainClass = VirtualCurrency.class;
	}

	@Override
	public List<VirtualCurrency> saveToMysql(List<VirtualCurrency> list) {
		return virtualCurrencyMysqlDao.save(list);
	}

}
