package com.reqres.utilities;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;

import io.restassured.response.Response;

public class RestUtils {
	public static String userName() {
		String generatedString = RandomStringUtils.randomAlphabetic(2);
		return ("smith" + generatedString);
	}		
	public static String userJob() {
		String generatedJob = RandomStringUtils.randomAlphabetic(5);
		return ("Job" + generatedJob);
	}
	// verify the http response status returned. check Status code is 200 ???
	public static void  checkStatusIs200(Response res) {
		Assert.assertEquals(res.getStatusCode(), 200, "Status Check Failed!");
	}

	// verify the http response status returned. check Status code is 200 ???
	public static void  checkStatusCode(int code, Response res) {
		Assert.assertEquals(res.getStatusCode(), code, "Status Check Failed!");
	}

	public static void checkContentType(String contentType, Response res) {
		Assert.assertEquals(res.header("Content-Type"), contentType, "Content Type Failed!!!");
	}
}