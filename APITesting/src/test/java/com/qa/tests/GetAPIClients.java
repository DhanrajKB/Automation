package com.qa.tests;

import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.clients.RestClient;
import com.qa.utils.TestUtils;

public class GetAPIClients extends TestBase {
	
	RestClient restClient;
	TestUtils testUtils;
	String url;
	String urlForPage2;
	
	@BeforeMethod
	public void setup() {
		url = prop.getProperty("serviceUrl") + prop.getProperty("apiUrl");
		urlForPage2 = prop.getProperty("serviceUrl") + prop.getProperty("apiUrl4Page2");
		restClient = new RestClient();
	}
	
	
	@Test(priority=1)
	public void getHttpResponseANDStatusCode(){
		System.out.println(">>>>>>>>>>>>>  Test Case :---->>>> getHttpResponseANDStatusCode");
		CloseableHttpResponse httpResponse =  restClient.getHttpResponse(url);
		System.out.println("HttpResponse :--->> " + httpResponse);
		int statusCode = restClient.getStatusCode(url);
		System.out.println("Status code of the response :--->>> " + statusCode);
		String responseInTxtFormat = restClient.getResponseInTextFormat(url);
		System.out.println("Text format response :--->>> " + responseInTxtFormat);
	}
	
	
	@Test(priority = 2)
	public void getJsonResponse(){
		System.out.println(">>>>>>>>>>>>>  Test Case :---->>>> getJsonResponse");
		JSONObject jResponse = restClient.getJsonResponse(url);
		System.out.println("JSONResponse :--->>> " + jResponse);
	}
	
	@Test(priority=3)
	public void getHeadersofTheJSONResponse(){
		System.out.println(">>>>>>>>>>>>>  Test Case :---->>>> getHeadersofTheJSONResponse");
		JSONObject jsonResponse = restClient.getJsonResponse(url);
		CloseableHttpResponse httpResponse = restClient.getHttpResponse(url);
		HashMap allHeaders = restClient.getHeaders(httpResponse);
		System.out.println("Headers :--->>> " + allHeaders);
	}
	
	@Test(priority=4)
	public void getJsonResponseByPassingHeaders(){
		System.out.println(">>>>>>>>>>>>>  Test Case :---->>>> getJsonResponseByPassingHeaders");
		HashMap<String, String> headersMap = new HashMap<String, String>();
		headersMap.put("Content-Type", "application/json");
//		headersMap.put("username", "dhanrajtest");
//		headersMap.put("password", "agasthya");
//		headersMap.put("Auth token", "test1234");
		JSONObject jsonRes = restClient.getJsonResponse(url, headersMap);
		System.out.println(jsonRes);
		
	}
	
	@Test(priority=5)
	public void jsonPostRequest(){
		
	}
	
//	@Test (priority=4)
	public void getValueInArrayOfJSON(){
		System.out.println(">>>>>>>>>>>>>  Test Case :---->>>> getValueInArrayOfJSON");
		JSONObject jsonResponse = restClient.getJsonResponse(prop.getProperty("singleUserUrl")+"/2");
		String valueOfJpath = testUtils.getValueOfJPath(jsonResponse, "/data{0}/last_name");
		System.out.println("Value of Jpath :--->>>>> " + valueOfJpath);
	}
	
	
	
	
	
	
	
	
}
