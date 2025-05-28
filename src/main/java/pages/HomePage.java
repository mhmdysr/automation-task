package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    WebDriver driver;
    WebDriverWait driverWait;

    public HomePage(WebDriver driver){
        this.driver=driver;
        this.driverWait=new WebDriverWait(driver,Duration.ofSeconds(20));
    }

    private  final By homoLogo=By.cssSelector("li[class='nav-item active']");
    private  final By categoriesSection=By.xpath("//a[text()='CATEGORIES']");

    public Boolean isHomePageDisplayed(){
        try {

            driverWait.until(ExpectedConditions.visibilityOfElementLocated(homoLogo));
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(categoriesSection));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

}
