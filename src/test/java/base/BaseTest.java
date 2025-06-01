package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
public class BaseTest {


    protected WebDriver driver;

    @BeforeClass
    public void LaunchBrowser(){
        driver =new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com");
    }


    @AfterClass
    public void tearDown()  {
        driver.manage().deleteAllCookies();
        driver.quit();

    }
}
