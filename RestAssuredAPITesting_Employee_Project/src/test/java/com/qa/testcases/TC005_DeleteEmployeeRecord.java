package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import com.qa.base.TestBase;
import com.qa.utilities.RestUtils;

public class TC005_DeleteEmployeeRecord extends TestBase{

	public RequestSpecification httpRequest;
	public Response response;
	public String empId;
	public RestUtils restUtils;

	@BeforeTest
	public void setup(){
		logger.info("#####------------- TC005_DeleteEmployeeRecord starts ----------######");
		restUtils = new RestUtils();
		empId = prop.getProperty("empid");
		RestAssured.baseURI = prop.getProperty("employeeBaseURI");
		httpRequest = RestAssured.given();
	}


	@Test
	public void deleteRecord() throws InterruptedException{
		logger.info("------------ deleting employee record ----------------");
		response = httpRequest.request(Method.DELETE,prop.getProperty("deleteEmployee")+"/"+empId);
		Thread.sleep(6000);
		System.out.println("Response body is :----> \n" + response.body().asString());
		logger.info("Response body is :----> \n" + response.body().asString());
	}


	@Test(dependsOnMethods="deleteRecord")
	public void validateResponseBody(){
		logger.info("--------- validating response body -------------");
		String successMsg = response.body().asString();
		if (successMsg.contains("successfully! deleted Records")) 
			logger.info("Delete the employee record successfully");
		else
			logger.warn("Delete of employee record failed, the empid is :--->>> " + empId);
	}


	@Test(dependsOnMethods="deleteRecord")
	public void validateContentType(){
		logger.info("--------- validating content type ---------------");
		String contentType = response.getHeader("Content-Type");
		if(contentType.equalsIgnoreCase("text/html; charset=UTF-8"))
			logger.info("Content-Type is :--->> " + contentType);
		else
			logger.warn("Content-Type is not as expected :--->> " + contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}

	@Test(dependsOnMethods="deleteRecord")
	public void validateStatusLineAndStatusCode(){
		logger.info("--------- validating Status line and status code ---------------");
		String statusLine = response.getStatusLine();
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");	
	}

	@Test(dependsOnMethods="deleteRecord")
	public void validateServerType(){
		logger.info("--------- validating server type --------");
		String server = response.getHeader("server");
		Assert.assertEquals(server, "nginx/1.16.0");
	}


	@AfterTest
	public void teardown(){
		logger.info("--------- validating response body -------------");
		response = httpRequest.request(Method.GET,prop.getProperty("singleEmployee")+"/"+empId);
		try {
			if (response.body().asString().equalsIgnoreCase(empId)) 
				logger.warn("Deleted record of empID - '" + empId + "', is still exists in DB");
			else
				logger.info("Deleted record of empID - '" + empId + "', not exists in Db");
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("#####------------- TC005_DeleteEmployeeRecord ends ----------######");
	}

}
