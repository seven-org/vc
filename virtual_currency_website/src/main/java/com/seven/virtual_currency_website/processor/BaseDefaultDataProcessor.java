package com.seven.virtual_currency_website.processor;

public abstract class BaseDefaultDataProcessor<D> extends AbstractDefaultDataProcessor<D>{
	
//	//处理数据(数据为json或htmldocument，string) 
//	public abstract D doDataProcess(String str);
	
//	//存入DB
//	public abstract void toDB(D datas);
	
	//数据去重操作
	/*
	 * 2.若此时cache中包含数据  则从cache取出最新的数据(根据key列表)
	 * 		4.将所有数据放入cache
	 * 		3.将cache中数据与步骤1中处理完成的数据进行比对，去除重复的数据
	 * 返回去重后的数据
	 */
	public D dataReduce(D datas){
		assert(datas!=null);
		D dataFromCache = getDataFromCache(datas);
		toCache(datas);
		compareReduce(datas, dataFromCache);
		return datas;
	}
	
	//存入cache
	public abstract void toCache(D caches);
	
	//从cache获取数据,若没有则返回null
	public abstract D getDataFromCache(D datas);
	
	//比对并去重(从datas中去重，不添加dataFromCache的新数据)
	public abstract D compareReduce(D datas, D dataFromCache);
	
//	//用去重后的数据生产一组MQ消息，存入MQ
//	public abstract void toMQ(D datas);

}
