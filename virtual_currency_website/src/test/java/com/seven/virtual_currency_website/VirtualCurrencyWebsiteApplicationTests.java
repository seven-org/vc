package com.seven.virtual_currency_website;

import java.io.IOException;
import java.util.ArrayList;
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
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.seven.virtual_currency_website.domain.JubiJsonObject;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class VirtualCurrencyWebsiteApplicationTests {

	String url = "https://data.btcchina.com/data/ticker?market=all";
	String url2 = "https://plus-api.btcchina.com/market/ticker?symbol=ETHCNY";
	String urlContent = "{'ticker_btccny':{'high':'28937.00','low':'27500.00','buy':'28650.14','sell':'28651.00','last':'28651.00','vol':'11058.34380000','date':1503945598,'vwap':'28353.89','prev_close':'28800.01','open':'28901.99'},'ticker_ltccny':{'high':'429.99','low':'384.51','buy':'416.01','sell':'421.50','last':'421.50','vol':'42629.19600000','date':1503945598,'vwap':'410.61','prev_close':'408.99','open':'408.99'},'ticker_ltcbtc':{'high':'0.01560000','low':'0.01370000','buy':'0.01420000','sell':'0.01560000','last':'0.01560000','vol':'40.40000000','date':1503945598,'vwap':'0.0147','prev_close':'0.0137','open':'0.0138'}}";
	
	String json_test = "{'max':540.6,'min':460.1,'sum':109900.3306,'d':[['18:02:16',512.99,0.0005,'sell','2017-09-03'],['18:02:16',512.99,2.409,'sell','2017-09-03'],['18:02:14',512.99,0.197,'sell','2017-09-03'],['18:02:14',512.99,1.8535,'buy','2017-09-03'],['18:02:00',513,7.736,'buy','2017-09-03'],['18:01:53',509.03,1.8553,'sell','2017-09-03'],['18:01:51',513.99,1.1951,'sell','2017-09-03'],['18:01:51',513.99,0.3049,'sell','2017-09-03'],['18:01:51',514.36,1.6951,'sell','2017-09-03'],['18:01:50',514.36,7.7765,'buy','2017-09-03'],['18:01:50',514,15.6344,'buy','2017-09-03'],['18:01:50',513.99,0.5,'sell','2017-09-03'],['18:01:38',513.99,192.7217,'buy','2017-09-03'],['18:01:38',513.8,2.3776,'buy','2017-09-03'],['18:01:38',513.8,1.998,'buy','2017-09-03'],['18:01:38',511.56,0.1964,'buy','2017-09-03'],['18:01:38',510,5.6163,'buy','2017-09-03'],['18:01:29',509,1,'sell','2017-09-03'],['18:01:26',509,0.257,'buy','2017-09-03'],['18:01:25',509,4.99,'buy','2017-09-03'],['18:01:22',509,1.5,'buy','2017-09-03'],['18:01:21',509,10,'buy','2017-09-03'],['18:01:19',509,1.5,'buy','2017-09-03'],['18:01:15',508,10,'buy','2017-09-03'],['18:01:12',507,1.1828,'sell','2017-09-03'],['18:01:11',507,1.2419,'buy','2017-09-03'],['18:01:07',507,0.5005,'buy','2017-09-03'],['18:01:03',507,13.2576,'buy','2017-09-03'],['18:01:03',507,2.3924,'buy','2017-09-03'],['18:00:49',507,0.1897,'buy','2017-09-03'],['18:00:49',506.04,0.0003,'buy','2017-09-03'],['18:00:46',506.04,0.77,'buy','2017-09-03'],['18:00:43',505.99,5.8649,'buy','2017-09-03'],['18:00:41',506.02,7.1178,'buy','2017-09-03'],['18:00:41',506,0.0002,'buy','2017-09-03'],['18:00:40',506,5.657,'buy','2017-09-03'],['18:00:35',506,1,'sell','2017-09-03'],['18:00:17',505.4,2.017,'sell','2017-09-03'],['18:00:17',505.41,7.896,'sell','2017-09-03'],['18:00:17',506,7.077,'sell','2017-09-03'],['18:00:13',506,8,'buy','2017-09-03'],['17:59:43',507,18.5589,'buy','2017-09-03'],['17:59:43',507,34.482,'buy','2017-09-03'],['17:59:43',506.9,16.9571,'buy','2017-09-03'],['17:59:23',505,0.1,'sell','2017-09-03'],['17:59:15',505,0.1,'sell','2017-09-03'],['17:58:53',506,1.176,'sell','2017-09-03'],['17:58:47',507,1.178,'buy','2017-09-03'],['17:58:46',506,4.2853,'sell','2017-09-03'],['17:58:43',507,2.252,'buy','2017-09-03']],'volume':55310541.143449}";
	
	public  Document getDocument (String url){
		try {
			return Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	@Autowired
//	private WebsiteURL websiteURL;
//	
//	@Autowired
//	private WebsiteForProcessorComponent websiteForProcessorComponent;
	
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
//		List<String> ls = websiteForProcessorComponent.websiteURL.getVirtualCurrency();
//		List<String> ls = websiteURL.getVirtualCurrency();
//		for (String s : ls){
//			System.out.println(s);
//		}
		JSONObject obj = new JSONObject(json_test);
		JSONArray ja = obj.getJSONArray("d");
		List<JubiJsonObject> jblist = new ArrayList<JubiJsonObject>();
		for (int i = 0; i < ja.length(); i++){
			jblist.add(jsonarrayToObject(ja.getJSONArray(i)));
		}
		System.out.println(obj);
		System.out.println(ja);
		
	}
	
//	public BaseVirtualCurrency jsonarrayToBaseVirtualCurrency(JSONArray ja){
//		BaseVirtualCurrency bvc = new BaseVirtualCurrency();
//		bvc.setTime(ja.getString(0));
//		bvc.setPrice(ja.getDouble(1));
//		bvc.setDoubleCount(ja.getDouble(2));
//		bvc.setOperation(ja.getString(3));
//		bvc.setDay(ja.getString(4));
//		return bvc;
//	}
	public JubiJsonObject jsonarrayToObject(JSONArray ja){
//		return new JubiJsonObject(ja.getString(0), ja.getDouble(1), ja.getDouble(2), ja.getString(3), ja.getString(4));
		return null;
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
