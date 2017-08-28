package com.seven.virtual_currency_website;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.seven.virtual_currency_website.component.WebsiteForProcessorComponent;
import com.seven.virtual_currency_website.entity.vc_website.WebsiteURL;

@RunWith(SpringRunner.class)
@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
public class VirtualCurrencyWebsiteApplicationTests {

	String url = "https://data.btcchina.com/data/ticker?market=all";
	String url2 = "https://plus-api.btcchina.com/market/ticker?symbol=ETHCNY";
	
	public  Document getDocument (String url){
		try {
			return Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Autowired
	private WebsiteURL websiteURL;
	
	@Autowired
	private WebsiteForProcessorComponent websiteForProcessorComponent;
	
	@Test
	public void contextLoads() throws Exception {
////		Document doc = this.getDocument(url);
//		ObjectMapper mapper = new ObjectMapper(); // just need one
//		  // Got a Java class that data maps to nicely? If so:
////		  FacebookGraph graph = mapper.readValue(url, FaceBookGraph.class);
//		  // Or: if no class (and don't need one), just map to Map.class:
//		Map<String,Object> map = mapper.readValue(url, Map.class);
//		System.out.println(map.toString());
		
//		test2();
//		System.out.println("-----------after test 2-------------");
		List<String> ls = websiteForProcessorComponent.websiteURL.getVirtualCurrency();
//		List<String> ls = websiteURL.getVirtualCurrency();
		for (String s : ls){
			System.out.println(s);
		}

	}
	
	public void test1() throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
//            HttpGet httpget = new HttpGet("http://httpbin.org/");
            HttpGet httpget = new HttpGet(url);

            System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
        } finally {
            httpclient.close();
        }
    }
	
	public void test2() throws Exception {
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		// Start the client
	    httpclient.start();
	    
	    

		// One most likely would want to use a callback for operation result
	    final CountDownLatch latch1 = new CountDownLatch(2);
	    final HttpGet request2 = new HttpGet(url);
	    final HttpGet request3 = new HttpGet(url2);
	    
	    
	    FutureCallback<HttpResponse> callback = new FutureCallback<HttpResponse>() {

	        public void completed(final HttpResponse response2) {
	            latch1.countDown();
	            System.out.println(request2.getRequestLine() + "->" + response2.getStatusLine());
	            
	            HttpEntity entity = response2.getEntity();
	            String responseString = null;
				try {
					responseString = EntityUtils.toString(entity, "UTF-8");
				} catch (ParseException | IOException e) {
					e.printStackTrace();
				}
	            System.out.println(responseString);
	        }

	        public void failed(final Exception ex) {
	            latch1.countDown();
	            System.out.println(request2.getRequestLine() + "->" + ex);
	        }

	        public void cancelled() {
	            latch1.countDown();
	            System.out.println(request2.getRequestLine() + " cancelled");
	        }

	    };
	    
	    httpclient.execute(request2, callback);
	    httpclient.execute(request3, callback);
	    
	    latch1.await();
	    System.out.println("----------------------------------------");

	}
	

}
