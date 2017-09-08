package com.seven.virtual_currency_website.dao.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisDao {
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	
	public void save(String key, Object obj){
		redisTemplate.opsForValue().set(key, obj);
	}
	
	public String get(String key){
		return stringRedisTemplate.opsForValue().get(key);
	}
	
}
