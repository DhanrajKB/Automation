package com.qa.testcases;


import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import com.qa.base.TestBase;

/******************************************************
Test Name:Get a single employee data
URI: http://dummy.restapiexample.com/api/v1/employee/{id}
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

public class TC002_GetSingleEmployeeData extends TestBase {
	
	public RequestSpecification httpRequest;
	public Response response;
	public String empID;
	
	@BeforeClass
	public void setup(){
		logger.info("#####-------- TC002_GetSingleEmployeeData starts --------######");
		empID = prop.getProperty("empid");
		RestAssured.baseURI = prop.getProperty("employeeBaseURI");
		httpRequest = RestAssured.given();
	}
	
	
	@Test(priority=0)
	public void getResponse(){
		logger.info("--------- getting response ----------");
		response = httpRequest.request(Method.GET,prop.getProperty("singleEmployee")+"/"+empID);
		logger.info("Response is :-->>> \n" + response.body().asString());
	}
	
	@Test(dependsOnMethods="getResponse")
	public void checkResponse(){
		logger.info("------- Checking response body --------");
		String id = response.body().jsonPath().get("id");
		System.out.println("Response empid is :--->>> " + id);
		Assert.assertEquals(id, empID);
	}
	
	@Test(dependsOnMethods="getResponse")
	public void checkStatusCode_StatusLine(){
		logger.info("-------- checking status line and status code -------");
		int statusCode = response.getStatusCode();
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		Assert.assertTrue(statusCode==200);
	}
	
	@Test(dependsOnMethods="getResponse")
	public void checkContentType(){
		logger.info("--------- checking content type ------------");
		String contentType = response.getHeader("Content-Type");
		logger.info("Content Type :--->>>> " + contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}
	
	@Test(dependsOnMethods="getResponse")
	public void checkContentLength(){
		logger.info("--------- validating content length");
		String contentLength = response.getHeader("Content-Length");
		Assert.assertTrue(Integer.parseInt(contentLength)<2000);
		
	}
	
	@AfterTest
	public void teardown(){
		logger.info("#####------- TC002_GetSingleEmployeeData ends ------#######");
	}

}
