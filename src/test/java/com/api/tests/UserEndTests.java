package com.api.tests;

import org.apache.logging.log4j.core.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.Base;
import com.api.endpoints.UserEndPoints;
import com.api.logs.LoggerManager;
import com.api.payload.UsersPayLoad;

import io.restassured.response.Response;

public class UserEndTests extends Base {
	private static final Logger log = (Logger) LoggerManager.getLogger(UserEndPoints.class);

	@Test(priority = 1)
	public void createUserTest() {
		
		log.info("********** Creating User **********");
		
		// creating payload using UsersPayLoad class
		UsersPayLoad payLoad = new UsersPayLoad("John Doe", "johndoe@example", "male", "active");

		// calling create user method from UserEndPoints class
		Response response = UserEndPoints.createUser(payLoad);

		// validating response

		Assert.assertEquals(response.getStatusCode(), 201);
		Assert.assertNotNull(response.jsonPath().getInt("id"));
		response.prettyPrint();
		
		log.info("********** User Created Successfully **********");
	}
	
	@Test(priority = 2)
	public void getUserTest() {
		
		log.info("********** Getting User Info **********");
		int userID = 1; // Replace with a valid user ID
		Response response = UserEndPoints.getUser(userID);
		Assert.assertEquals(response.getStatusCode(), 200);
		response.prettyPrint();
		log.info("********** User Info Retrieved Successfully **********");
	}
	
	@Test
	public void updateUserTest() {
		log.info("********** Updating User Info **********");
		// creating payload using UsersPayLoad class
		UsersPayLoad payLoad = new UsersPayLoad("Pawan","pawan@gmail.com","Male","active");
		// calling update user method from UserEndPoints class
		Response response = UserEndPoints.updateUser(1, payLoad);
		//validating response
		Assert.assertEquals(response.getStatusCode(), 200);
		response.prettyPrint();
		log.info("********** User Info Updated Successfully **********");
	}
	
	@Test(priority = 4)
	public void deleteUserTest() {
		log.info("********** Deleting User **********");
		//int userID=1; // Replace with a valid user ID;
		Response response = UserEndPoints.deleteUser(1);
		Assert.assertEquals(response.getStatusCode(), 200);
		response.prettyPrint();
		log.info("********** User Deleted Successfully **********");
		
	}
	
	public void patchUserTest() {
		log.info("********** Patching User Info **********");
		// creating payload using UsersPayLoad class
		UsersPayLoad payLoad = new UsersPayLoad("Pawan Patil","pawanpatil@gmail.com","Male","active");
		// calling patch user method from UserEndPoints class
		Response response = UserEndPoints.patchUser(1, payLoad);
		//validating response
		Assert.assertEquals(response.getStatusCode(), 200);
		response.prettyPrint();
		log.info("********** User Info Patched Successfully **********");
	}

}
