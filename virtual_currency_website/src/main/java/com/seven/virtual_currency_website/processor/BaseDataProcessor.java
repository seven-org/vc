package com.seven.virtual_currency_website.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seven.virtual_currency_website.dao.redis.VirtualCurrencyRedisDao;
import com.seven.virtual_currency_website.entity.vc.BaseVirtualCurrency;

@Component
public abstract class BaseDataProcessor extends AbstractDataProcessor<List<BaseVirtualCurrency>>{
	
	@Autowired
	private VirtualCurrencyRedisDao<BaseVirtualCurrency, String> virtualCurrencyRedisDao;
	
	@Autowired
	private RabbitTemplate template;

	@Autowired
	private FanoutExchange fanout;
	
	/*
	 * 从redis获取需要的数据
	 * note: redis key 为 name, 
	 * value为BaseVirtualCurrency 其中不含id字段
	 */
	private List<BaseVirtualCurrency> getDataFromRedis(List<BaseVirtualCurrency> datas){
		Collection<String> names = new ArrayList<String>();
		for (BaseVirtualCurrency bvc : datas){
			names.add(bvc.getName());
		}
		return virtualCurrencyRedisDao.multiGet(names);
	}
	
	private void putDataToRedis(List<BaseVirtualCurrency> bvcs){
		virtualCurrencyRedisDao.multiSave(bvcs);
	}
	
	
	public abstract List<BaseVirtualCurrency> process(String hr);
	
	@Override
	public void doDataProcess(String str){
		//处理数据
		List<BaseVirtualCurrency> datas = process(str);
		
		try {
			//存入db
			toDB(datas);
			
			//获取redis中相同key的数据
			List<BaseVirtualCurrency> bvcs = getDataFromRedis(datas);
			
			//找到不重复的数据
//		List<BaseVirtualCurrency> bvcsave = new ArrayList<BaseVirtualCurrency>();
			datas.removeAll(bvcs);
			
			//将不重复的数据存入(替换原有)redis
			putDataToRedis(datas);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//将不重复的数据加入MQ
		//TODO
		if (datas.size() != 0){
			try {
				ObjectMapper mapper = new ObjectMapper();
				String jsonMessage = mapper.writeValueAsString(datas);
				System.out.println(jsonMessage);
				template.convertAndSend(fanout.getName(), "stocks", jsonMessage);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
