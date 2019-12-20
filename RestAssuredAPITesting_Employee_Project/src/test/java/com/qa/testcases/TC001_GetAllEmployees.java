package com.qa.testcases;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import com.qa.base.TestBase;

/******************************************************
Test Name:Get all employees data
URI: http://dummy.restapiexample.com/api/v1/employees
Request Type: GET
Request Payload(Body): NA
********* Validations **********
Status Code : 200
Status Line : HTTP/1.1 200 OK
Content Type : text/html; charset=UTF-8
Server Type :  nginx/1.14.1
Content Encoding : gzip
Content Length <800
 *********************************************************/

public class TC001_GetAllEmployees extends TestBase {
	
	public RequestSpecification httpRequest;
	public Response response;
	public String empId;
	
	@BeforeTest
	public void setup() throws InterruptedException{
		System.out.println("####-------- TC001_GetAllEmployees starts -------#####");
		logger.info("####-------- TC001_GetAllEmployees starts -------#####");
		RestAssured.baseURI = prop.getProperty("employeeBaseURI");
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,prop.getProperty("allEmployees"));
		Thread.sleep(6000);
	}
	
	
	
	@Test
	public void getResponseBody(){
		System.out.println("----------- Cheking response --------------");
		logger.info("----------- Cheking response --------------");
		System.out.println("Response :--->>> \n" + response.body().asString());
		Assert.assertTrue(response.body()!=null);
		List<String> ids = response.body().jsonPath().get("id");
		System.out.println("Id's :----->>> " + ids);
	}
	
	@Test
	public void checkStatusLine_StatusCode(){
		logger.info("------------ checking status line and status code --------------");
		String statusLine = response.getStatusLine();
		int statusCode = response.getStatusCode();
		Assert.assertTrue(statusCode==200);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		logger.info("Status Code :---> " + statusCode);
		logger.info("Status Line :---> " + statusLine);
	}
	
	
	@Test
	public void checkResponseTime() {
		logger.info("------------ Checking response time of the request------------");
		long responseTime = response.getTime();
		if (responseTime<800) {
			logger.warn("response time is less than 800ms");
		}
		logger.info("Response time :---> " + responseTime);
		Assert.assertTrue(responseTime<6000);
	}
	
	@Test
	public void checkContentType(){
		logger.info("------------ checking content type the header -----------");
		String contentType = response.getHeader("Content-Type");
		logger.info("Content Type :-->> " + contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}
	
	@Test
	public void checkServer(){
		logger.info("----------- checking server type of the header ----------");
		String serverType = response.getHeader("Server");
		logger.info("Server type :--->>> " + serverType);
		Assert.assertEquals(serverType, "nginx/1.16.0");
	}
	
	@AfterTest
	public void teardown(){
		System.out.println("####-------- TC001_GetAllEmployees ends -------#####");
		logger.info("####-------- TC001_GetAllEmployees ends -------#####");
	}
	

}
