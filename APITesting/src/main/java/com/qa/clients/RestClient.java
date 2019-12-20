package com.qa.clients;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.qa.utils.TestUtils;

public class RestClient {
	
	TestUtils testUtils;
	CloseableHttpClient httpClient;
	CloseableHttpResponse httpResponse;
	
	public CloseableHttpResponse getHttpResponse(String url){
		httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		try {
			httpResponse = httpClient.execute(httpGet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return httpResponse;
	}
	
	public int getStatusCode(String url){
		int statusCode = getHttpResponse(url).getStatusLine().getStatusCode();
		return statusCode;
	}
	
	public String getResponseInTextFormat(String url){
		String response = null; 
		try {
			response = EntityUtils.toString(getHttpResponse(url).getEntity());
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}


	/* Method 			: getJsonResponse ---->>> without headers
	 * Parameter		: api url
	 * Pre-Condition	: 
	 * Return 			: JSONObject
	 */
	public JSONObject getJsonResponse(String url){
		httpResponse = getHttpResponse(url);
		String response = null;
		try {
			response = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsonResponse = new JSONObject(response);
		return jsonResponse;
	}
	
	
	public HashMap getHeaders(CloseableHttpResponse httpResponse){
		Header[] headers =  httpResponse.getAllHeaders(); 
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headers) {
			allHeaders.put(header.getName(), header.getValue());
		}
		return allHeaders;
	}
	
	/* Method 			: getJsonResponse ---->>> with headers (overriding getJsonResponse method)
	 * Parameter		: api url & hashmap contains the headers
	 * Pre-Condition	: 
	 * Return 			: JSONObject
	 */
	public JSONObject getJsonResponse(String url, HashMap<String, String> headerMap){
		httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		//-------- addding headers to the request before url execution
		for(Map.Entry<String, String> header : headerMap.entrySet()){
			httpGet.addHeader(header.getKey(), header.getValue());
		}
		//----------------------------------------
		try {
			httpResponse = httpClient.execute(httpGet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String response = null;
		try {
			response = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsonResponse = new JSONObject(response);
		return jsonResponse;
		
	}
	
	
	/* Method 			: getJsonResponse ---->>> with headers (overriding getJsonResponse method)
	 * Parameter		: api url & hashmap contains the headers
	 * Pre-Condition	: 
	 * Return 			: JSONObject
	 */
	
	public CloseableHttpResponse postRequest(String url, String payLoad, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url); //---- HttpPost request
		httpPost.setEntity(new StringEntity(payLoad)); //------- set payload of the post request before executing uri
		for(Map.Entry<String, String> entry : headerMap.entrySet()){ //------ add the header to post request
			httpPost.addHeader(entry.getKey(),entry.getValue());
		}
		httpResponse = httpClient.execute(httpPost);
		System.out.println(httpResponse);
		return httpResponse;		
	}
		
	
	
	
	
	
	
	
	
	

}