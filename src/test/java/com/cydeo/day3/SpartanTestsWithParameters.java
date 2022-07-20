package com.cydeo.day3;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithParameters {



    //BeforeAll is an annotation equals to @BeforeClass in testNg, we use with static method name
    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we don't need to type each http method.
        RestAssured.baseURI = "http://18.204.201.165:8000";
    }














}
