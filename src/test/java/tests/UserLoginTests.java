package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.UserLoginPage;
import org.json.simple.JSONObject;
import utils.TestDataUtil;

public class UserLoginTests extends BaseTest {

    @Test
    public void testSuccessfulLoginWitJsonData(){

        JSONObject loginData = TestDataUtil.getLoginData();
        String username = (String) loginData.get("username");
        String password = (String) loginData.get("password");


        UserLoginPage loginPage=new UserLoginPage(driver);
        loginPage.clickLoginBtn();
        loginPage.setUsernameAndPassword(username,password);
        loginPage.clickSubmitBtn();

        // compare with the actual/Expected Logged UserName
        String actualLoggedUserName = loginPage.getLoggedUserName();
        String expectedLoggedUserName  ="Welcome "+username;

        System.out.println("the actual Logged UserName is "+actualLoggedUserName);
        Assert.assertEquals(actualLoggedUserName,expectedLoggedUserName,"The Actual UserName not as same the Expected UserName ,");


    }

}
