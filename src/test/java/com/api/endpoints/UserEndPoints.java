package com.api.endpoints;

import com.api.payload.UsersPayLoad;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class UserEndPoints {

	// Post User
	public static Response createUser(UsersPayLoad payLoad) {

		return given().
				contentType("application/json").
				body(payLoad).
				when().post("users/").
				then().extract().response();
	}
	
	// Get User
	public static Response getUser(int userID) {
	return	given().
		pathParam("userID", userID).
		when().
		get("users/{userID}").
		then().
		extract().response();
	}
	
	// Update User
	public static Response updateUser(int userID, UsersPayLoad payLoad) {
		return given().
		contentType("application/json").
		pathParam("userID", userID).
		body(payLoad).
		when().
		put("users/{userID}").
		then().
		extract().response();
		
	}
	
	//delete User
	
	public static Response deleteUser(int userID) {
		return given().
		pathParam("userID", userID).
		when().
		delete("users/{userID}").
		then().
		extract().response();
	}
	
	public static Response patchUser(int userID, UsersPayLoad payLoad) {
		return given().
		contentType("application/json").
		pathParam("userID", userID).
		body(payLoad).
		when().
		patch("users/{userID}").
		then().
		extract().response();
	}
}
