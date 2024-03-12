package day1;

import org.testng.annotations.Test;

import  static io.restassured.RestAssured.*;
import  static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;



public class Httprequest {

    @Test (priority = 1)
    void getUsers() {
                when()
                .get("https://reqres.in/api/users?page=2")

                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .log().all();
    }

    @Test (priority = 2)
    void creatUser(){

        given()
                .when()
                .then();
    }


}
