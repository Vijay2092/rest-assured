package day2;

import groovy.lang.DelegatesTo;
import io.restassured.response.Response;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.when;


import  static io.restassured.RestAssured.*;
import  static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class cookiesDemo {

  //  @Test
    void TestCokkies() {

        when()
                .get("https://www.google.com/")

                .then()
                .cookies("AEC", "Ae3NU9N7393tdxtnWC9mGq1y4VpSikeK0_d9_jUezMj5rktA1ySD7RnfrLQ")
                 .log().all();
    }

  //  @Test
    void getCokkiesInfo(){

        Response res = given()
                .when()
                .get("https://www.google.com/");

                //get single cookie info
               // String cookieValue = res.getCookie("AEC");
             //   System.out.println(cookieValue);

                //get all cookie info
          Map<String, String> cookies = res.getCookies();
          for (Map.Entry<String, String> entry : cookies.entrySet()){
              System.out.println(entry.getKey() + ":" + entry.getValue());
          }
    }


    //parsing rsponse body with validations
    @Test
    void response (){

        Response res = given()
                .contentType("ContentType.JSON")

                .when()
                .get("https://petstore.swagger.io/v2/user/login?username=vijay&password=vijay");

       // Assert.assertEquals(res.getStatusCode(),500);

        //for capture object value we iterate over an jsonobjects and we will get value
        JSONObject jo = new JSONObject(res.toString());
        for(int i =0; i<jo.getJSONArray("user").length(); i++){
            jo.getJSONArray("user").getJSONObject(i).get("namw").toString();
        }






    }


    

}
