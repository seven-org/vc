package com.seven.virtual_currency_website.processor.realsiteprocessor;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.seven.virtual_currency_website.dao.redis.RedisDao;
import com.seven.virtual_currency_website.domain.JubiJsonObject;
import com.seven.virtual_currency_website.processor.ListBaseDefaultDataProcessor;

/*
 * jubi网默认数据处理类
 */
@Component
public abstract class DefaultJubiJsonProcessor<T extends JubiJsonObject, E extends JpaRepository<T, ?>> extends ListBaseDefaultDataProcessor<T>{

	private E jubiDao;
	
	@Autowired
	private RedisDao redisDao;
	
	private String redis_key = "default_jubi_key";
	
	private Class<T> clazz;
	
	public E getJubiDao() {
		return jubiDao;
	}

	public abstract void setJubiDao(E jubiDao);

	public String getRedis_key() {
		return redis_key;
	}

	public abstract void setRedis_key(String redis_key);

	@Override
	public void toCache(List<T> caches) {
		JSONArray ja = new JSONArray(caches);
		redisDao.save(redis_key, ja.toString());
	}

	@Override
	public List<T> getDataFromCache(List<T> datas) {
		ObjectMapper mapper = new ObjectMapper();
		List<T> jbList = null;
		String str = redisDao.get(redis_key);
		try {
			jbList = mapper.readValue(str, TypeFactory.defaultInstance().constructCollectionType(List.class, clazz));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jbList;
	}

	@Override
	public List<T> doDataProcess(String str) {
		JSONObject obj = new JSONObject(str);
		JSONArray ja = obj.getJSONArray("d");
		List<T> jbbtclist = new ArrayList<T>();
		for (int i = 0; i < ja.length(); i++){
			jbbtclist.add(jsonarrayToObject(ja.getJSONArray(i)));
		}
		return jbbtclist;
	}
	
	private T jsonarrayToObject(JSONArray ja){
		T t = null;
		try {
			Constructor c = clazz.getConstructor(String.class, double.class, double.class, String.class, String.class);
			t = (T) c.newInstance(ja.getString(0), ja.getDouble(1), ja.getDouble(2), ja.getString(3), ja.getString(4));
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public void toDB(List<T> datas) {
		jubiDao.save(datas);
	}

	@Override
	public void toMQ(List<T> datas) {
		// TODO Auto-generated method stub
		
	}

}
