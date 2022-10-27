package com.reqres.testCases;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.reqres.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC05_Delete_User_Record extends TestBase {
	
	@BeforeClass	
	void deleteUserRecord() throws InterruptedException { 			  // process of request send
		logger.info("------- Started TC05_Delete_User_Record -------");  // specify URI 
		RestAssured.baseURI= "https://reqres.in";  				  // request specification object --> initialize
		httpReq = RestAssured.given();							  //httpRequest will be used to send the request
		response = httpReq.request(Method.DELETE,"/api/users/2");
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
		int statusCode = response.getStatusCode();
		logger.info("Status Code ==> " +  statusCode);
		AssertJUnit.assertEquals(statusCode, 204);
	}
	
	@Test
	void checkResponseTime() {
		logger.info("------- Check Response Time -------");
		long responseTime = response.getTime();
		logger.info("Response time is ==> " +  responseTime);
		if (responseTime > 6000)
			logger.warn("Response Time is greater than 6000");
		AssertJUnit.assertTrue(responseTime < 6000);
	}
	@Test
	void checkStatusLine() {
		logger.info("------- Check Status Line -------");
		String statusLine = response.getStatusLine();
		logger.info("status Line ==> " + statusLine);
		AssertJUnit.assertEquals(statusLine, "HTTP/1.1 204 No Content");
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
		logger.info("------- Finished TC05_Delete_User_Record -------");
	}
}