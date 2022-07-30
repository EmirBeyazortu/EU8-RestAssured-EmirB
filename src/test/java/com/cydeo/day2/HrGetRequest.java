package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HrGetRequest {

    //BeforeAll is an annotation equals to @BeforeClass in testNg, we use with static method name
        @BeforeAll
    public static void init(){
            //save baseurl inside this variable so that we don't need to type each http method.
            RestAssured.baseURI = "http://54.91.11.180:1000/ords/hr";
        }


        @DisplayName("GET request to / regions")
        @Test
        public void test1(){

            Response response = RestAssured.get("/regions");

            //print the status code
            System.out.println("response.statusCode() = " + response.statusCode());
        }

        /*
            Given Accept type is json
            When user sends get request to regions/2
            Then response status code must be 200
            And body is json format
            And response body contains Americas
         */

    @DisplayName("GET request to / regions/2")
    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON).when().get("/regions/2");

        //verify status code
        assertEquals(200, response.statusCode());

        //verift content type
        assertEquals("application/json", response.contentType());

        response.prettyPrint();

        //verify body contains Americas
        assertTrue(response.body().asString().contains("Americas"));


    }





}
