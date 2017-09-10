package com.seven.virtual_currency_website.processor;

import java.util.List;

public abstract class ListBaseDefaultDataProcessor<D> extends BaseDefaultDataProcessor<List<D>>{

	@Override
	public List<D> compareReduce(List<D> datas, List<D> dataFromCache) {
		datas.removeAll(dataFromCache);
		return datas;
	}

}
