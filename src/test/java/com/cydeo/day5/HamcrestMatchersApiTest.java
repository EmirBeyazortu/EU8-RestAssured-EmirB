package com.cydeo.day5;


import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersApiTest {


    @DisplayName("OneSpartan with Hamcrest and chaining")
    @Test
    public void test1(){

        /*
        given accept type is Json
        And path param id is 15
        When user sends a get request to spartans/{id}
        Then status code is 200
        And content type is Json
        And json data has following
            "id": 15,
            "name": "Meta",
            "gender": "Female",
            "phone" : 1938695106
         */

        given().
                accept(ContentType.JSON)
                .and().pathParam("id",15)
                .when()
                .get("http://18.204.201.165:8000/api/spartans/{id}")
                .then()
                        .statusCode(200)
                .and().assertThat()//assertThat() is a sugar method here
                .contentType("application/json")
                .and()
                .body("id" , equalTo(15),
                        "name", is("Meta"), "gender", is("Female"),
                        "phone", is(1938695106));




    }

    @DisplayName("CBTraining Teacher request with chaining and matchers")
    @Test
    public void teacherData(){

        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id",20452)
                .when()
                .get("http://api.cybertektraining.com/teacher/{id}")
                .then()
                    .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                .header("Content-Length", is("236"))
                .and()
                .header("Date", notNullValue())
                .and().assertThat() //Syntactic sugar
                .body("teachers[0].firstName", is("Alexander"))
                .body("teachers[0].lastName" , is("Syrup"))
                .body("teachers[0].gender", equalTo("male"));






    }

    @DisplayName("GET request to teacher/all and chaining")
    @Test
    public void teachersTest(){

        //verify Alexander, Darleen, Sean inside the all teachers
        given()
                    .accept(ContentType.JSON)
                .when()
                    .get("http://api.cybertektraining.com/teacher/all")
                .then()
                .statusCode(200)
                .and()
                .body("teachers.firstName", hasItems("Alexander", "Rabia", "Marlyn"));

    }







}
