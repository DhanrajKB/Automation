package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;
import com.qa.base.TestBase;
import com.qa.clients.RestClient;
import com.qa.data.Users;

public class PostApiTest extends TestBase{

	TestBase testBase;
	RestClient restClient;
	String url;
	String urlForPage2;
	CloseableHttpResponse httpResponse;
	
	@BeforeMethod
	public void setup(){
		testBase = new TestBase();
		restClient = new RestClient();
		url = prop.getProperty("serviceUrl") + prop.getProperty("apiUrl");
		urlForPage2 = prop.getProperty("serviceUrl") + prop.getProperty("apiUrl4Page2");
		restClient = new RestClient();
	}
	
	
	@Test
	public void postRequest() throws JsonGenerationException, JsonMappingException, IOException{
		// ---- create headers for the payload
		HashMap<String, String> headerMap = new HashMap<String,String>();
		headerMap.put("Content-Type", "application/json");
		
		Users users = new Users("Agasthya", "PM");
		//--- jackson api
		ObjectMapper objMapper = new ObjectMapper();  //----- create object mapper using jackson api
		//--- object to json file
		objMapper.writeValue(new File(userdir+"\\src\\main\\java\\com\\qa\\data\\Users.json"), users); //--- creates json file using users class fields
		//--- object to JSON string
		String userJsonString = objMapper.writeValueAsString(users); //---- creates json string using users fields
		
		//---- call post method from restclient 
		httpResponse = restClient.postRequest(url, userJsonString, headerMap);	
		
		//--- status code//
		System.out.println("Status code of the post request :-->> " + httpResponse.getStatusLine().getStatusCode());
		
		//--- get into UTF-8
		String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		System.out.println(responseString);
		
		//--- convert the response to JSON format
		JSONObject jsonResponse = new JSONObject(responseString);
		System.out.println("Json Response :-->>> " + jsonResponse);
		
		//--- now convert JSON response to JSON object which helps to validate against the payload
		//--- JSON to Java object
		Users respUsers = objMapper.readValue(responseString, Users.class);
		System.out.println(respUsers);
		
		//---- Validation against the payload
		Assert.assertEquals(users.getName(), respUsers.getName(), "Name validated and the response 'Name' is :-->>> " + respUsers.getName());
		Assert.assertEquals(users.getJob(), respUsers.getJob(), "Name validated and the response 'job' is :-->>> " + respUsers.getJob());
		System.out.println("Name validated and the response 'Name' is :-->>> " + respUsers.getName());
		System.out.println("Name validated and the response 'job' is :-->>> " + respUsers.getJob());
		System.out.println("Name validated and the response 'createdAt' is :-->>> " + respUsers.getCreatedAt());
		System.out.println("Name validated and the response 'Id' is :-->>> " + respUsers.getId());
		
		
	}
	
	
	
}
