package com.cydeo.day4;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithPath extends HRTestBase {

    @DisplayName("Get request to countries whit Path method")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .queryParam("q","{\"region_id\":2}")
                .when()
                .get("/countries");

        assertEquals(200, response.statusCode());

        //print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        //print hasMore
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        //print first CountryId
        String firstCountryId = response.path("items[0].country_id");

        System.out.println("firstCountryId = " + firstCountryId);

        //print second country name
        String secondCountryName = response.path("items[1].country_name");

        System.out.println("secondCountryName = " + secondCountryName);

        //print "http://18.204.201.165:1000/ords/hr/countries/CA"
        String CanadaLink = response.path("items[2].links[0].href");

        System.out.println("CanadaLink = " + CanadaLink);

        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

        //assert that all regions ids are equal to 2
        List<Integer> allRegionsIDS = response.path("items.region_id");

        for (Integer regionID : allRegionsIDS) {
            System.out.println("regionID = " + regionID);
            assertEquals(2,regionID);
        }


    }


    @DisplayName("GET request to / employees with Query Param")
    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON).and()
                .queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .log().all().when().get("/employees");

        assertEquals(200, response.statusCode());
        //assertEquals("application/json", response.header("Content-Type"));
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("IT_PROG"));

        //make sure we have only IT_PROG as a job_id
        List<String> allJobIDs = response.path("items.job_id");

        for (String jobID : allJobIDs) {
            System.out.println("JobID = " + jobID);
            assertEquals("IT_PROG", jobID);
        }

    }




        @Test
        public void test3(){

            //TASK
            //print each name of IT_PROGs

            Response response = given().accept(ContentType.JSON).and()
                    .queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                    .log().all().when().get("/employees");

            List<String> itProgsNames = response.path("items.first_name");


            for (String name : itProgsNames) {
                System.out.println("name = " + name);
            }


        }


















}
