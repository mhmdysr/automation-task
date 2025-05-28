package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import pages.HomePage;

public class BaseTest {


    protected WebDriver driver;

    @BeforeClass
    public void LaunchBrowser(){
        driver =new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com");
       // homePage=new HomePage(driver);

    }


    @AfterClass
    public void tearDown()  {
        driver.manage().deleteAllCookies();
        driver.quit();

    }
}
