package com.seven.virtual_currency_website.schedule;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.seven.virtual_currency_website.component.WebsiteForProcessorComponent;
import com.seven.virtual_currency_website.processor.DefaultDataProcessor;

/*
 * 主任务类
 * 
 * 1.每隔一段时间，向List中存在的所有站点发送 一次 http 请求，
 * 
 * 2.将获取到的response进行数据的处理
 * 筛选出可用的数据并封装为实体类
 * 
 * 3.将实体类存入DB，并从缓存中获取相应数据信息
 * 
 * 4.数据比对
 * 
 * 5.若比对后发现diff，将缓存中数据替换
 * 
 * 6.发送消息到MQ
 * 
 */
@Service
public class ScheduledTaskService implements InitializingBean, ApplicationContextAware{
	
	@Autowired
	private WebsiteForProcessorComponent websiteForProcessorComponent;
	
	private ApplicationContext ac;
	
	private Map<String, Class<?>> processorMap;

//	@Scheduled(cron = "10 38 14 ? * *")
    @Scheduled(fixedDelay = 30000)
	public void getWebsiteInformation() {
		
		//通过httpclient向注册的website发送http 异步 请求
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		// Start the client
	    httpclient.start();
		
		//获取提前注册的websiteURL列表
		List<String> websiteURLs = websiteForProcessorComponent.websiteURL.getVirtualCurrency();
		
		final CountDownLatch latch1 = new CountDownLatch(websiteURLs.size());
//		List<HttpGet> requests = new ArrayList<HttpGet>(websiteURLs.size());
		
		for (String URLstr : websiteURLs) {
			HttpGet request = new HttpGet(URLstr);
//			requests.add(request);
			//创建callback方法
			FutureCallback<HttpResponse> callback = new FutureCallback<HttpResponse>() {

		        public void completed(final HttpResponse response2) {
		            latch1.countDown();
		            System.out.println(request.getRequestLine() + "->" + response2.getStatusLine());
		            
		            HttpEntity entity = response2.getEntity();
		            String responseString = null;
					try {
						responseString = EntityUtils.toString(entity, "UTF-8");
					} catch (ParseException | IOException e) {
						e.printStackTrace();
					}
		            System.out.println(responseString);
		            
		            //获取到相应数据后处理数据
		            ((DefaultDataProcessor<?>) ac.getBean(processorMap.get(URLstr))).doDataProcess(responseString);
		        }

		        public void failed(final Exception ex) {
		            latch1.countDown();
		            System.out.println(request.getRequestLine() + "->" + ex);
		        }

		        public void cancelled() {
		            latch1.countDown();
		            System.out.println(request.getRequestLine() + " cancelled");
		        }

		    };
			httpclient.execute(request, callback);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		processorMap = websiteForProcessorComponent.loadProcessMap();
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.ac = arg0;
	}
}
