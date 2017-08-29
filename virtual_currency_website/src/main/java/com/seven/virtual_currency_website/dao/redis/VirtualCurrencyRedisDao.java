package com.seven.virtual_currency_website.dao.redis;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	ValueOperations<String, T> valOps;
	
	
	public void save(T t){
		valOps.set(t.getId(), t);
	}
	
	public T getOne(ID id){
		return (T) valOps.get(id);
	}
	
	public void multiSave(List<T> ts){
		Map<String, T> m = new HashMap<String, T>();
		for (T t : ts){
			m.put(t.getName(), t);
		}
		valOps.multiSet(m);
	}
	
	public List<T> multiGet(Collection<String> ids){
		return valOps.multiGet(ids);
	}

}
