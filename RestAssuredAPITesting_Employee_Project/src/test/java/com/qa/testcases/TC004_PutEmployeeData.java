/******************************************************
Test Name:Update an employee record
URI: http://dummy.restapiexample.com/api/v1/update/{id}
Request Type: PUT
Request Payload(Body): {"name":"XXXXX","salary":"XXXX","age":"XX"}
********* Validations **********
Response Payload(Body) : {"name":"XXXXX","salary":"XXXX","age":"XX"}
Status Code : 200
Status Line : HTTP/1.1 200 OK
Content Type : text/html; charset=UTF-8
Server Type :  nginx/1.14.1
Content Encoding : gzip
**********************************************************/

package com.qa.testcases;
import org.json.simple.JSONObject;
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

public class TC004_PutEmployeeData extends TestBase{
	
	public RequestSpecification httpRequest;
	public Response response;
	public RestUtils restUtils;
	public String empName,empAge,empSalary,empID;
	
	
	@BeforeTest
	public void setup(){
		logger.info("####------- TC004_PutEmployeeData starts -----------#####");
		empID = prop.getProperty("empid");
		restUtils = new RestUtils();
		empName = restUtils.getEmpName();
		empAge = restUtils.getAge();
		empSalary = restUtils.getSalary();
		//------- create request
		RestAssured.baseURI = prop.getProperty("employeeBaseURI");
		httpRequest = RestAssured.given();
	}
	
	
	@Test
	public void putRequest() throws InterruptedException{
		logger.info("--------- updating employee record ---------------");
		//------- create payload
		JSONObject payload = new JSONObject();
		payload.put("name", empName);
		payload.put("age", empAge);
		payload.put("salary", empSalary);
		//---- create header
		httpRequest.header("Content-Type","application/json");
		//---- attach payload to request
		httpRequest.body(payload.toJSONString());
		//----- send put request and get response
		response = httpRequest.request(Method.PUT,prop.getProperty("updateEmployee")+"/"+empID);
		Thread.sleep(6000);
		System.out.println("Response body is :--->>> \n" + response.body().asString());
		logger.info("Response body is :--->>> \n" + response.body().asString());	
	}
	
	@Test(dependsOnMethods="putRequest")
	public void validateResponseBody(){
		logger.info("--------- validating response body ---------------");
		String name,age,salary,id;
		name = response.body().jsonPath().getString("name");
		age = response.body().jsonPath().getString("age");
		salary = response.body().jsonPath().getString("salary");
		id = response.body().jsonPath().getString("id");
		Assert.assertEquals(name, empName);
		Assert.assertEquals(age, empAge);
		Assert.assertEquals(salary, empSalary);
		logger.info("Response name,age,salary & id is -->>>> " + name + ", "
				+ age + ", "
				+ salary + ", "
				+ id);
	}
	
	@Test(dependsOnMethods="putRequest")
	public void validateContentType(){
		logger.info("--------- validating content type ---------------");
		String contentType = response.getHeader("Content-Type");
		if(contentType.equalsIgnoreCase("text/html; charset=UTF-8"))
			logger.info("Content-Type is :--->> " + contentType);
		else
			logger.warn("Content-Type is not as expected :--->> " + contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}
	
	@Test(dependsOnMethods="putRequest")
	public void validateStatusLineAndStatusCode(){
		logger.info("--------- validating Status line and status code ---------------");
		String statusLine = response.getStatusLine();
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");	
	}
	
	@Test(dependsOnMethods="putRequest")
	public void validateServerType(){
		logger.info("--------- validating server type --------");
		String server = response.getHeader("server");
		Assert.assertEquals(server, "nginx/1.16.0");
	}
	
	@AfterTest
	public void teardown(){
		logger.info("#####---------- TC004_PutEmployeeData ends ----------######");
	}

}
