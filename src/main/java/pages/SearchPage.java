package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage {

    WebDriver driver;
    WebDriverWait driverWait;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    //Locators

    private final By productTitles = By.cssSelector("h4[class=\"card-title\"]");

    //Methods
    public boolean isProductDisplayed(String productName) {
        boolean isFound = false;

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        int listCount = driver.findElements(productTitles).size();

        for (int i = 0; i < listCount; i++) {
            String nameOfProduct = driver.findElements(productTitles).get(i).getText();
            if (nameOfProduct.equalsIgnoreCase(productName)) {
                isFound = true;
                break;

            }

        }
        return isFound;

    }

}
