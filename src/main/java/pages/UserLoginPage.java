package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserLoginPage {

    WebDriver driver;
    WebDriverWait driverWait;
    public UserLoginPage(WebDriver driver){
        this.driver=driver;
        this.driverWait=new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    //Locators
    private final By loginBtn=By.id("login2");
    private final By usernameField=By.id("loginusername");
    private final By passwordField=By.id("loginpassword");
    private final By submitLoginBtn=By.cssSelector("button[onclick=\"logIn()\"]");
    private final By usernameText=By.id("nameofuser");

    //Methods

    public void clickLoginBtn(){
        driver.findElement(loginBtn).click();
    }

    public void setUsernameAndPassword(String username ,String password){


        //set the username field
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);

        //set the password field
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);

    }
    public void clickSubmitBtn(){
        driver.findElement(submitLoginBtn).click();
    }

    public String getLoggedUserName(){

        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(usernameText)).getText();
    }

}
