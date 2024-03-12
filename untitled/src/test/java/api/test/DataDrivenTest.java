package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.beust.ah.A;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import api.utilities.DataProviders;

public class DataDrivenTest {

    @Test (priority = 1 ,dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String UserID, String UserName, String FirstName, String LastName,String Email, String Password, String Phone) {
        User userPayLoad = new User();

        userPayLoad.setId(Integer.parseInt(UserID));
        userPayLoad.setusername(UserName);
        userPayLoad.setFirstname(FirstName);
        userPayLoad.setLastname(LastName);
        userPayLoad.setEmail(Email);
        userPayLoad.setPassword(Password);
        userPayLoad.setNumber(Phone);

        Response response = UserEndPoints.createUser(userPayLoad);
        Assert.assertEquals(response.getStatusCode(),200);

    }

    @Test (priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteByUserName(String userName){
Response response = UserEndPoints.deleteUser(userName);
Assert.assertEquals(response.getStatusCode(),200);
    }
}
