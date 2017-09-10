package com.seven.virtual_currency_website.processor.realsiteprocessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.seven.virtual_currency_website.dao.mysql.JubiBtcDao;
import com.seven.virtual_currency_website.dao.redis.RedisDao;
import com.seven.virtual_currency_website.domain.JubiBtc;
import com.seven.virtual_currency_website.processor.ListBaseDefaultDataProcessor;

@Component
public class JubiJsonProcessor extends ListBaseDefaultDataProcessor<JubiBtc>{

	@Autowired
	private JubiBtcDao jubiBtcDao;
	
	@Autowired
	private RedisDao redisDao;
	
	private final String redis_key = "jubibtc";
	
	@Override
	public void toCache(List<JubiBtc> caches) {
		/*
		 * 直接转换jsonarray存入
		 */
		JSONArray ja = new JSONArray(caches);
		redisDao.save(redis_key, ja.toString());
	}

	@Override
	public List<JubiBtc> getDataFromCache(List<JubiBtc> datas) {
		ObjectMapper mapper = new ObjectMapper(); // just need one
		List<JubiBtc> jbList = null;
		String str = redisDao.get(redis_key);
		try {
			jbList = mapper.readValue(str, TypeFactory.defaultInstance().constructCollectionType(List.class, JubiBtc.class));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jbList;
	}

	@Override
	public List<JubiBtc> doDataProcess(String str) {
		JSONObject obj = new JSONObject(str);
		JSONArray ja = obj.getJSONArray("d");
		List<JubiBtc> jbbtclist = new ArrayList<JubiBtc>();
		for (int i = 0; i < ja.length(); i++){
			jbbtclist.add(jsonarrayToObject(ja.getJSONArray(i)));
		}
		return jbbtclist;
	}

	@Override
	public void toDB(List<JubiBtc> datas) {
		jubiBtcDao.save(datas);
	}

	@Override
	public void toMQ(List<JubiBtc> datas) {
		// TODO Auto-generated method stub
		
	}
	
	private JubiBtc jsonarrayToObject(JSONArray ja){
		return new JubiBtc(ja.getString(0), ja.getDouble(1), ja.getDouble(2), ja.getString(3), ja.getString(4));
	}

}
