package com.qa.testcases;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import com.google.gson.JsonObject;
import com.qa.base.TestBase;
import com.qa.utilities.RestUtils;

public class TC003_PostEmployeeRecord extends TestBase{
	
	public RequestSpecification httpRequest;
	public Response response;
	public RestUtils restUtils;
	public String empName;
	public String empSalary;
	public String empAge;
	
	@BeforeTest
	public void setup(){
		logger.info("####--------- TC003_PostEmployeeRecord starts -------######");
		restUtils = new RestUtils();
		//------ get baseuri and create request
		RestAssured.baseURI = prop.getProperty("employeeBaseURI");
		httpRequest = RestAssured.given();
		empName = restUtils.getEmpName();
		empSalary = restUtils.getSalary();
		empAge = restUtils.getAge();
	}
	
	@Test
	public void postRequest() throws InterruptedException{
		//--- prepare payload
		JSONObject payload = new JSONObject();
		payload.put("name", empName);
		payload.put("salary", empSalary);
		payload.put("age", empAge);
		
		//---- add header to request
		httpRequest.header("Content-Type","application/json");
		
		//--- attach payload to request
		httpRequest.body(payload.toJSONString());
		
		//---- send request and get response
		response = httpRequest.request(Method.POST,prop.getProperty("createEmployee"));
		Thread.sleep(6000);
		System.out.println("Response is :-->>> " + response.body().toString());
		logger.info("Response is :-->>> " + response.body().toString());
	}
	
	
	@Test(dependsOnMethods="postRequest")
	public void validateResponse(){
		logger.info("------ validating response body --------");
		String name,salary,age;
		name = response.body().jsonPath().getString("name");
		salary = response.body().jsonPath().getString("salary");
		age = response.body().jsonPath().getString("age");
		Assert.assertEquals(name, empName);
		Assert.assertEquals(salary, empSalary);
		Assert.assertEquals(age, empAge);
		logger.info("Response id, name,age & Salary :--->>> " + response.body().jsonPath().getString("id") + ", "
				+ name + ", "
				+ age + ", "
				+ salary + ", ");
	}
	
	
	@AfterTest
	public void teardown(){
		logger.info("####--------- TC003_PostEmployeeRecord ends -------######");
	}

}
