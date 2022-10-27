package com.reqres.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.reqres.base.TestBase;
import com.reqres.utilities.JSONReading;
import com.reqres.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC03_Create_User extends TestBase {
	public static JSONReading jsonReading = new JSONReading();
	
	@BeforeClass	
	void createUser() throws InterruptedException { 			  // process of request send
		logger.info("------- Started TC03_Create_User -------");  // specify URI 
		RestAssured.baseURI= "https://reqres.in";  				  // request specification object --> initialize
		httpReq = RestAssured.given();							  //httpRequest will be used to send the request
		JSONObject reqParam = new JSONObject();
		String jsonFile = "C:\\workspace\\APIAutomationFramework\\APIAutomationFramework\\src\\test\\java\\com\\reqres\\utilities\\JSONReading.java";
		//		reqParam.put("name", jsonReading.getJSONData("name","jsonFile"));
		//		reqParam.put("job", jsonReading.getJSONData("job","jsonFile"));

		reqParam.put("name", "abc");
		reqParam.put("job", "xyz");
		httpReq.header("content-Type", "application/json");
		httpReq.body(reqParam.toJSONString());
		response = httpReq.request(Method.POST,"/api/users");
		Thread.sleep(3);

	}
	@Test
	void checkResponseBody() {
		logger.info("------- Check Response Body -------");
		String responseBody = response.getBody().asPrettyString();
		logger.info("Response Body ==> " +  responseBody);
		AssertJUnit.assertTrue(responseBody != null);
	}
	@Test
	void checkStatusCode() {
		logger.info("------- Check Status Code -------");
		int StatusCode = response.getStatusCode();
		logger.info("Status Code ==> " +  StatusCode);
		RestUtils.checkStatusCode(201, response);
	}
	@Test
	void checkResponseTime() {
		logger.info("------- Check Response Time -------");
		long responseTime = response.getTime();
		logger.info("Response time is ==> " +  responseTime);
		if (responseTime > 5000)
			logger.warn("Response Time is greater than 5000");
		AssertJUnit.assertTrue(responseTime < 5000);
	}
	@Test
	void checkStatusLine() {
		logger.info("------- Check Status Line -------");
		String statusLine = response.getStatusLine();
		logger.info("status Line ==> " + statusLine);
		AssertJUnit.assertEquals(statusLine, "HTTP/1.1 201 Created");
	}
	@Test
	void checkContentType() {
		logger.info("------- Check Content Type -------");
		String contentType = response.header("Content-Type");
		logger.info("content Type is : " + contentType);
		AssertJUnit.assertEquals(contentType, "application/json; charset=utf-8");
	}
	@Test
	void checkServer() {
		logger.info("------- Check Server -------");
		String server = response.header("Server");
		logger.info("Server is : " + server);
		Assert.assertEquals(server, "cloudflare");
	}
	@AfterClass
	void tearDown() {
		logger.info("------- Finished TC03_Create_User -------");
	}
}