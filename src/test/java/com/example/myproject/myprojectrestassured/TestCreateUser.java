package com.example.myproject.myprojectrestassured;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class TestCreateUser {

    private final String CONTEXT_PATH = "/mobile-app-ws";

    @BeforeEach
    void setUp() throws Exception{
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    final void testCreateUser(){

        List<Map<String, Object>> userAddresses = new ArrayList<>();

        Map<String, Object> shippingAddress = new HashMap<>();
        shippingAddress.put("city", "Vancouver");
        shippingAddress.put("country", "Canada");
        shippingAddress.put("streetName", "123 Street Name");
        shippingAddress.put("postalCode", "123456");
        shippingAddress.put("type", "shipping");

        userAddresses.add(shippingAddress);

        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("firstName", "Te");
        userDetails.put("lastName", "ta");
        userDetails.put("email", "ta@g.com");
        userDetails.put("password", "123");
        userDetails.put("addresses", userAddresses);


        Response response = given().
        contentType("application/json").
        accept("application/json").
        body(userDetails).
        when().
        post(CONTEXT_PATH + "/users").
        then().
        statusCode(200).
        contentType("application/json").
        extract().
        response();

        String userId = response.jsonPath().getString("userId");
        assertNotNull(userId);

    }

}
