package com.api.base;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;

public class Base {
	@BeforeClass
	public void setup() {

		// setting base URI

		RestAssured.baseURI = ConfigReader.getProperty("baseURL");

		// Set default content type, logging, etc.

		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}

}
