package com.cydeo.day4;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class CBTrainingApiWithJsonPath {

    //BeforeAll is an annotation equals to @BeforeClass in testNg, we use with static method name
    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we don't need to type each http method.
        baseURI = "http://api.cybertektraining.com";
    }

    @DisplayName("GET Request to individual student")
    @Test
    public void test1() {

        //send a request to student id 32881 as a path parameter and accept header application/json
        //verify status code / content type / Content-Encoding = gzip
        //verify Date header exists
        //assert that
            /*
            firstName Vera
            batch 14
            section 12
            emailAddress aaa@gmail.com
            companyName Cybertek
            state IL
            zipcode 60606

            using JsonPath
             */

        Response response = given().accept(ContentType.JSON).and().pathParams("id", 32881)
                .when().get("/student/{id}");

        response.prettyPrint();

        assertEquals(200, response.statusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());
        assertEquals("gzip", response.header("Content-Encoding"));

        boolean isPresent = response.getHeaders().hasHeaderWithName("Vary");
        assertTrue(isPresent);


        JsonPath jsonPath = response.jsonPath();

        String firstName = jsonPath.getString("students[0].firstName");
        System.out.println(firstName);
        assertEquals("Vera", firstName);

        int batch = jsonPath.getInt("students[0].batch");
        System.out.println(batch);
        assertEquals(14,batch);

        int section = jsonPath.getInt("students[0].section");
        System.out.println(section);
        assertEquals(12, section);

        String emailAddress = jsonPath.getString("students[0].contact.emailAddress");
        System.out.println(emailAddress);
        assertEquals("aaa@gmail.com", emailAddress);

        String companyName = jsonPath.getString("students[0].company.companyName");
        System.out.println(companyName);
        assertEquals("Cybertek", companyName);

        String state = jsonPath.getString("students[0].company.address.state");
        System.out.println(state);
        assertEquals("IL", state);

        int zipCode = jsonPath.getInt("students[0].company.address.zipCode");
        System.out.println(zipCode);
        assertEquals(60606, zipCode);


    }
}
