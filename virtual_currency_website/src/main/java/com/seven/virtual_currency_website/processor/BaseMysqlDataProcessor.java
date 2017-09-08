//package com.seven.virtual_currency_website.processor;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.BeanUtils;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import com.seven.virtual_currency_website.entity.component.DbResult;
//import com.seven.virtual_currency_website.entity.vc.BaseVirtualCurrency;
//
//@Deprecated
//public abstract class BaseMysqlDataProcessor<S, T extends JpaRepository<S, ?>> extends BaseDataProcessor{
//	
//	public Class<S> domainClass;
//	
//	public abstract void setClass();
//	
//	public List<S> saveToMysql(List<S> list){
//		return null;
//	}
//	
//	public T mysqlJpaRepository;
//	
//	/*
//	 * 弃用
//	 */
//	@Deprecated
//	@Override
//	public DbResult<?> toDB(List<BaseVirtualCurrency> datas){
//		setClass();
//		/*
//		 * 定义持久化方法(mysql)
//		 */
//		List<S> vcs = new ArrayList<S>();
//		for (BaseVirtualCurrency bvc : datas){
//			S vc = null;
//			try {
//				vc = domainClass.newInstance();
//			} catch (InstantiationException | IllegalAccessException e) {
//				e.printStackTrace();
//			}
//			BeanUtils.copyProperties(bvc, vc);
//			vcs.add(vc);
//		}
//		
//		DbResult<List<S>> dr = new DbResult<List<S>>();
//		try {
////			vcs = saveToMysql(vcs);
//			List<S> list = saveToMysql(vcs);
//			dr.setJpaSaveResult(list);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return dr;
//	}
//	
//}
