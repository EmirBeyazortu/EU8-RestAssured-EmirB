package com.cydeo.day6;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import groovy.lang.DelegatesTo;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class SpartanPojoGetRequestTest extends SpartanTestBase {

        @DisplayName("GET one spartan and convert it to Spartan Object")
        @Test
        public void oneSpartanPojo(){

            Response response = given().accept(ContentType.JSON)
                    .and().pathParam("id", 15)
                    .when().get("/api/spartans/{id}")
                    .then().statusCode(200)
                    .extract().response();


            //De serialize - - > JSON to POJO (java custom class)
            //2 different way to do this
            //1.using as() method
            //we convert json response to spartan object wıth the help of jackson
            //as() method uses jackson to de serialize (converting JSON to Java class)
            Spartan spartan15 = response.as(Spartan.class);
            System.out.println(spartan15);
            System.out.println("spartan15.getId() = " + spartan15.getId());
            System.out.println("spartan15.getGender() = " + spartan15.getGender());

            //second way of deserialize json to java
            //2.using JsonPath to deserialize to custom class


            JsonPath jsonPath = response.jsonPath();

            Spartan s15 = jsonPath.getObject("", Spartan.class);
            System.out.println(s15);
            System.out.println("s15.getName() = " + s15.getName());
            System.out.println("s15.getPhone() = " + s15.getPhone());

        }

        @DisplayName("GET one spartan from search endpoint result and use POJO")
        @Test
        public void spartanSearchWithPojo(){





        }












}
