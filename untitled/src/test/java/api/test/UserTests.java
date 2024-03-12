package api.test;

import org.testng.Assert;
import org.testng.FileAssert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

    Faker faker;
    User userPayload;

    @BeforeClass
    public void setupData() {
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setusername(faker.name().username());
        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setNumber(faker.phoneNumber().cellPhone());
    }

    @Test(priority = 1)
    public void testPostUser() {
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2)
    public void testGetUserByName() {
        Response response = UserEndPoints.readUser(this.userPayload.getusername());
        response.then().log().all();

       // Assert.assertTrue(response.getStatusCode() == 200);
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 3)
    public void testUpdateUserByName() {
        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPoints.updateUser(this.userPayload.getusername(), userPayload);
        response.then().log().all();
        //response.then().log().body().statusCode(200);

        Assert.assertEquals(response.getStatusCode(), 200);

        //checking data after updation
        Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getusername());
        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);

    }

    @Test(priority = 4)
    public void deleteUserByName() {
        Response response = UserEndPoints.deleteUser(this.userPayload.getusername());

        Assert.assertTrue(response.statusCode() == 200);
    }
}