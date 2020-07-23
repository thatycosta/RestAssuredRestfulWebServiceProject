package com.example.myproject.myprojectrestassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class UsersWebServiceEndpointTest {

    private final String CONTEXT_PATH = "/mobile-app-ws";
    private final String EMAIL_ADDRESS = "tauane@g.com";
    private final String JSON = "application/json";

    @BeforeEach
    void setUp() throws Exception{
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    final void testUserLogin(){

        Map<String, String> loginDetails= new HashMap<>();
        loginDetails.put("email", EMAIL_ADDRESS);
        loginDetails.put("password", "1235633");

        Response response = given().contentType(JSON).accept(JSON).body(loginDetails).when().post(CONTEXT_PATH + "/users/login").then().statusCode(200).extract().response();

        String authorizationHeader = response.header("Authorization");
        String UserId = response.header("UserId");

        assertNotNull(authorizationHeader);
        assertNotNull(UserId);
    }

}
