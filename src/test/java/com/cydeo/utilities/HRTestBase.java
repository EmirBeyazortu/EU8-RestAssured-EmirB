package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class HRTestBase {


    //BeforeAll is an annotation equals to @BeforeClass in testNg, we use with static method name
    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we don't need to type each http method.
        RestAssured.baseURI = "http://18.204.201.165:1000/ords/hr";
    }



}
