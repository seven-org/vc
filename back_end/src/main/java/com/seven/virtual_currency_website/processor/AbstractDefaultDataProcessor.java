package com.seven.virtual_currency_website.processor;

public abstract class AbstractDefaultDataProcessor<D> implements DefaultDataProcessor{
	
	/*
	 * 定义完整的操作流程
	 * 1.将json数据进行处理
	 * 		2.若此时cache中包含数据  则从cache取出最新的数据(根据key列表)
	 * 		3.将cache中数据与步骤1中处理完成的数据进行比对，去除重复的数据
	 * 		4.将去重后的数据放入cache(cache中仅存储一定限制数量的数据集)
	 * 
	 * 5.将去重后的数据放入DB
	 * 6.将去重后的数据装换为MQ消息，准备发送
	 */
	@Override
	public void process(String data){
		/*
		 * loaded 和 reduced 为同一个对象的两个不同时期的两个引用
		 */
		D loaded = doDataProcess(data);
		D reduced = dataReduce(loaded);
		toDB(reduced);
		toMQ(reduced);
	}
	
	//处理数据(数据为json或htmldocument，string) 
	public abstract D doDataProcess(String str);
	
	//存入DB
	public abstract void toDB(D datas);
	
	//数据去重操作
	public abstract D dataReduce(D datas);
//	//存入cache
//	public abstract void toCache(C caches);
	
	//用去重后的数据生产一组MQ消息，存入MQ
	public abstract void toMQ(D datas);

}
