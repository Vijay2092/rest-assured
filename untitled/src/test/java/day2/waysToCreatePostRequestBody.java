package day2;
import org.testng.annotations.Test;

import java.util.HashMap;

import  static io.restassured.RestAssured.*;
import  static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class waysToCreatePostRequestBody {

    //1 post requestbody using hashmap

    @Test
    void testPostUsingHashMap(){

        HashMap hm = new HashMap<>();
        hm.put("name", "Vijay");
        hm.put("location", "India");
        hm.put("mobile no" , "12345");

        String courseArr [] ={"Java", "QA"};
        hm.put("courses" , courseArr);

        given()
                .contentType("application/json")
                .body(hm)

                .when()
                .post("https://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name",equalTo("Vijay"))
                .body("location",equalTo("India"))
                .body("Mobile no", equalTo("12345"))
                .body("courses[0]",equalTo("Java"))
                .body("courses[1]",equalTo("QA"))
                .header("Content-type" ,"application/json;charset=utf-8")
                .log().all();
    }

    //  2. ------------post request body using org.json library
    /*same as hashmap method

    JSonObject data = new JSONObject();
    and we put data same as hashmap

    in given we need to covert that data into string format
    .body(data.toString());
     */


    //  3.------------- Post request using POJO class
    /*
    we will make pojo class and make setter and getter methods there
    In test method
    we call that class with creating new object like
     class_name data = new class_name();

     data.setName("Vijay");
     data.setLocation("India"):
     data.setMobile_No("12345");

     String courseArr [] = {"Java", "QA"};
     data.setCourses(courseArr);
     */

    // 4. Create Request Body using external JSON file.
    /*
    we will create json file
    we can call it by file path
     File f = new File("path"):
     FileReader fr = new FileReader(f);
     JSONTokener jt = new JSONTokener(fr);
     JSONObject data = new JSONObject(jt);
     */


    //https://reqres.in/api/user?page=2&id=5









}
