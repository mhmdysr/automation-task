package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductPage {


    WebDriver driver;
    WebDriverWait driverWait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    //Navigate to Product Details & Extract Information[Locators]
    private final By nokiaProductLink = By.xpath("//a[text()='Nokia lumia 1520']");
    private final By productName = By.cssSelector("h2[class=\"name\"]");
    private final By productPrice = By.cssSelector("h3[class=\"price-container\"]");
    private final By productDescription = By.xpath("//div[@id='more-information']//p");
    private final By selectedProductTitle = By.xpath("//tr[@class='success']//td[2]");
    private final By selectedProductTotalPrice = By.id("totalp");

    //Add Product to Cart & Verify Cart Count[Locators]
    private final By addToCartBtn = By.cssSelector("a[onclick=\"addToCart(2)\"]");
    private final By cartBtn = By.id("cartur");

    //Place oder [Locators]

    private final By placeOrderBtn = By.xpath("//*[text()='Place Order']");
    private final By orderNameField = By.id("name");
    private final By orderCountryField = By.id("country");
    private final By orderCityField = By.id("city");
    private final By orderCreditCardNum = By.id("card");
    private final By orderCreditCardMonth = By.id("month");
    private final By orderCreditCardYear = By.id("year");
    private final By purchaseOrderBtn = By.cssSelector("button[onclick=\"purchaseOrder()\"]");
    private final By confirmationPopUp = By.cssSelector("div[data-animation=\"pop\"]");
    private final By confirmationText = By.cssSelector("p.lead.text-muted");


    //Navigate to Product Details & Extract Information[Methods]
    public void clickNokiaProduct() {
        driverWait.until(ExpectedConditions.elementToBeClickable(nokiaProductLink)).click();
    }

    public String getProductName() {
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
    }

    public String getProductPrice() {
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(productPrice)).getText();

    }

    public String getProductDescription() {
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(productDescription)).getText();

    }


    //Add Product to Cart & Verify Cart Count[Methods]

    public void clickAddToCartBtn() {
        driverWait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();

    }

    public void handeAlertPopup(String action) {

        driverWait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();

        if (action.equalsIgnoreCase("accept")) {
            alert.accept();
        } else if (action.equalsIgnoreCase("dismiss")) {
            alert.dismiss();
        }

    }

    public void clickCartBtn() {
        driverWait.until(ExpectedConditions.elementToBeClickable(cartBtn)).click();

    }

    public String getSelectedProductTitle() {
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(selectedProductTitle)).getText();
    }

    public String getSelectedCartTotalPrice() {
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(selectedProductTotalPrice)).getText();

    }


    //Place oder [Methods]

    public void clickPlaceOrderBtn() {
        driverWait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();
    }

    public void fillCheckoutDetails(String orderName, String orderCountry, String orderCity, String creditCardNum, String creditCardMonth, String creditCardYear) {

        //enter the Name of order
        driverWait.until(ExpectedConditions.elementToBeClickable(orderNameField)).clear();
        driverWait.until(ExpectedConditions.elementToBeClickable(orderNameField)).sendKeys(orderName);

        //enter the Country of order
        driverWait.until(ExpectedConditions.elementToBeClickable(orderCountryField)).clear();
        driverWait.until(ExpectedConditions.elementToBeClickable(orderCountryField)).sendKeys(orderCountry);

        //enter the City of order
        driverWait.until(ExpectedConditions.elementToBeClickable(orderCityField)).clear();
        driverWait.until(ExpectedConditions.elementToBeClickable(orderCityField)).sendKeys(orderCity);

        //enter the CreditCard of order
        driverWait.until(ExpectedConditions.elementToBeClickable(orderCreditCardNum)).clear();
        driverWait.until(ExpectedConditions.elementToBeClickable(orderCreditCardNum)).sendKeys(creditCardNum);

        //enter the Month of order
        driverWait.until(ExpectedConditions.elementToBeClickable(orderCreditCardMonth)).clear();
        driverWait.until(ExpectedConditions.elementToBeClickable(orderCreditCardMonth)).sendKeys(creditCardMonth);

        //enter the Year of order
        driverWait.until(ExpectedConditions.elementToBeClickable(orderCreditCardYear)).clear();
        driverWait.until(ExpectedConditions.elementToBeClickable(orderCreditCardYear)).sendKeys(creditCardYear);


    }

    public List<String> getCheckoutFieldValues() {

        String name = driverWait.until(ExpectedConditions.elementToBeClickable(orderNameField)).getAttribute("value");
        String country = driverWait.until(ExpectedConditions.elementToBeClickable(orderCountryField)).getAttribute("value");
        String city = driverWait.until(ExpectedConditions.elementToBeClickable(orderCityField)).getAttribute("value");
        String cardNum = driverWait.until(ExpectedConditions.elementToBeClickable(orderCreditCardNum)).getAttribute("value");
        String cardYear = driverWait.until(ExpectedConditions.elementToBeClickable(orderCreditCardMonth)).getAttribute("value");
        String cardMonth = driverWait.until(ExpectedConditions.elementToBeClickable(orderCreditCardYear)).getAttribute("value");

        List<String> values = new ArrayList<>();
        values.add(name);
        values.add(country);
        values.add(city);
        values.add(cardNum);
        values.add(cardYear);
        values.add(cardMonth);

        return values;


    }


    //Handle Pop-up Confirmation & Validate Order Details [Methods]
    public void clickPurchaseOrderBtn() {
        driverWait.until(ExpectedConditions.elementToBeClickable(purchaseOrderBtn)).click();
    }

    public boolean isOrderConfirmationPopUpVisible() {
        try {
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(confirmationPopUp));
            return true;

        } catch (Exception e) {
            return false;
        }
    }


    //extract Data from The Confirmation screen [Methods]
    public String[] getConfirmationLines() {

        WebElement messageBox = driverWait.until(ExpectedConditions.visibilityOfElementLocated(confirmationText));
        String fullText = messageBox.getText();
        return fullText.split("\n");
    }

    public String getId() {
        String[] lines = getConfirmationLines();
        return lines[0].replace("Id: ", "");
    }

    public String getAmount() {
        String[] lines = getConfirmationLines();
        return lines[1].replace("Amount: ", "").replace(" USD", "");
    }

    public String getCardNumber() {
        String[] lines = getConfirmationLines();
        return lines[2].replace("Card Number: ", "");
    }

    public String getName() {
        String[] lines = getConfirmationLines();
        return lines[3].replace("Name: ", "");
    }

    public String getDay() {
        String[] lines = getConfirmationLines();
        String data = lines[4].replace("Date: ", "").trim();
        return data.split("/")[0];
    }

    public String getMonth() {
        String[] lines = getConfirmationLines();
        String data = lines[4].replace("Date: ", "").trim();
        return data.split("/")[1];
    }

    public String getYear() {
        String[] lines = getConfirmationLines();
        String data = lines[4].replace("Date: ", "").trim();
        return data.split("/")[2];
    }
    // Take ScreenSHoot [Methods]
    public void screenshotOfConfirmationPopup() throws IOException {
        //create a timestamp to avoid repeat the screenshot name
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Take screenshot of confirmation dialog
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.createDirectories(Paths.get("screenshots"));
        Files.copy(screenshot.toPath(), Paths.get("screenshots/confirmationPopup_" + timestamp + ".png"));

    }

    //Error Handling & Recovery Mechanisms [Methods]
    public String errorMsPopUp() {

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        webDriverWait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String msgError = alert.getText();
        alert.accept();
        return msgError;

    }


}
