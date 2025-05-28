package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomeTests extends BaseTest {


    @Test
    public void verifyHomePageLoadsSuccessfully(){

        HomePage homeTests=new HomePage(driver);
        Assert.assertTrue(homeTests.isHomePageDisplayed(),"Homepage did not load successfully.");


    }
}
