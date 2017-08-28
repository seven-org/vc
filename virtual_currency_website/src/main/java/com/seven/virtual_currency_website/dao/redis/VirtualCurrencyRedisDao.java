package com.seven.virtual_currency_website.dao.redis;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.seven.virtual_currency_website.entity.vc.BaseVirtualCurrency;

@Repository
public class VirtualCurrencyRedisDao <T extends BaseVirtualCurrency, ID> {
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Resource(name="stringRedisTemplate")
	ValueOperations<String, String> valOpsStr;
	
	@Autowired
	RedisTemplate<Object, Object> redisTemplate;
	
	@Resource(name="redisTemplate")
	ValueOperations<Object, Object> valOps;
	
	
	public void save(T t){
		valOps.set(t.getId(), t);
	}
	
	@SuppressWarnings("unchecked")
	public T getOne(ID id){
		return (T) valOps.get(id);
	}

}
