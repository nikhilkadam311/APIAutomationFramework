package com.reqres.testCases;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.reqres.base.TestBase;
import com.reqres.utilities.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC01_Get_User extends TestBase {

	@BeforeClass	
	void getAllUser() throws InterruptedException { // process of request send
		logger.info("------- Started TC01_Get_User -------");  // specify URI 
		RestAssured.baseURI= "https://reqres.in";  // request specification object --> initialize
		httpReq = RestAssured.given(); //httpRequest will be used to send the request
		response = httpReq.request(Method.GET,"/api/users?page=2");
		Thread.sleep(3);
	}
	@Test
	void checkResponseBody() {
		logger.info("------- Check Response Body -------");
		String responseBody = response.getBody().asPrettyString();
		logger.info("Response Body ==> " +  responseBody);
		Assert.assertTrue(responseBody != null);
	}
	@Test
	void checkStatusCode() {
		logger.info("------- Check Status Code -------");
		int StatusCode = response.getStatusCode();
		logger.info("Status Code ==> " +  StatusCode);
		RestUtils.checkStatusIs200(response);
	}
	@Test
	void checkResponseTime() {
		logger.info("------- Check Response Time -------");
		long responseTime = response.getTime();
		logger.info("Response time is ==> " +  responseTime);
		if (responseTime > 25000)
			logger.warn("Response Time is greater than 25000");
		Assert.assertTrue(responseTime < 25000);
	}
	@Test
	void checkStatusLine() {
		logger.info("------- Check Status Line -------");
		String statusLine = response.getStatusLine();
		logger.info("status Line ==> " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	@Test
	void checkContentType() {
		logger.info("------- Check Content Type-------");
		String contentType = response.header("Content-Type");
		logger.info("content Type is : " + contentType);
		RestUtils.checkContentType("application/json; charset=utf-8", response);
		//		AssertJUnit.assertEquals(contentType, "application/json; charset=utf-8");
	}
	@Test
	void checkServer() {
		logger.info("------- Check Server -------");
		String server = response.header("Server");
		logger.info("Server is : " + server);
		Assert.assertEquals(server, "cloudflare");
	}
	@Test
	void checkContentEncoding() {
		logger.info("------- Check Content Encoding -------");
		String contentEncoding = response.header("Content-Encoding");
		logger.info("Server is : " + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}
	@AfterClass
	void tearDown() {
		logger.info("------- Finished TC01_Get_User -------");
	}
}