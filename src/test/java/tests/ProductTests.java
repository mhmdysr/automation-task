package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ProductPage;

import java.io.IOException;


public class ProductTests extends BaseTest {

    ProductPage productPage;
    String name;
    String fullPriceText;
    String price;
    String productPrice;
    String description;


    @Test(priority = 1)
    public void tc_01_verifyProductDetails() {
        productPage = new ProductPage(driver);

        // the Website doesn't have a search bar, so instead i select a product from the products that Displayed in HomePage
        productPage.clickNokiaProduct();

        //Extract and log product details
        name = productPage.getProductName();
        fullPriceText = productPage.getProductPrice();
        price = fullPriceText.split(" ")[0];
        description = productPage.getProductDescription();

        System.out.println("Product Name: " + name);
        System.out.println("Product Price: " + price);
        System.out.println("Product Description: " + description);

        //Assertions to the selected product's details.
        Assert.assertEquals(name, "Nokia lumia 1520", "The selected product Name is not match the Actual product Name ");
        Assert.assertEquals(price, "$820", "The selected product price is not match the Actual product price ");
        Assert.assertTrue(description.length() > 15);

    }

    @Test(priority = 2)
    public void tc_02_addProductToCart() {

        // addProductToCart page
        productPage.clickAddToCartBtn();
        productPage.handeAlertPopup("accept");
        productPage.clickCartBtn();

        //Cart Page Assertions:
        productPrice = price.replace("$", "");
        String selectedProductTitle = productPage.getSelectedProductTitle();
        String selectedTotalPrice = productPage.getSelectedCartTotalPrice();

        // compare with the first price with the total price if they / =1 then we have only product and The Quantity is  accurate
        int selectedProductQuantity = Integer.parseInt(selectedTotalPrice) / Integer.parseInt(productPrice);

        Assert.assertEquals(name, selectedProductTitle, "The correct product Title is Not present");
        Assert.assertEquals(selectedProductQuantity, 1, "The product quantity is Not accurate");
        Assert.assertEquals(productPrice, selectedTotalPrice, "The cart total updates Not correctly");

        System.out.println("productPrice : " + productPrice);
        System.out.println("selectedTotalPrice : " + selectedTotalPrice);
        System.out.println("selectedProductQuantity : " + selectedProductQuantity);


    }

    @DataProvider(name = "placeOrderData")
    public Object[][] getPlaceOrder() {
        return new Object[][]{
                {"yasser's order", "Egypt", "Giza", "4111111111111111", "10", "2026"}
        };
    }

    @Test(priority = 3, dataProvider = "placeOrderData")
    public void tc_03_placeOrderAndValidateOrderData(String orderName, String orderCountry, String orderCity, String cardNum, String cardMonth, String cardYear) {

        // Click the “Place Order” button
        productPage.clickPlaceOrderBtn();

        //Send data using data provider
        productPage.fillCheckoutDetails(orderName, orderCountry, orderCity, cardNum, cardMonth, cardYear);

        // Extract and validate data before submission
        String actualOrderName = productPage.getCheckoutFieldValues().get(0);
        String actualOrderCountry = productPage.getCheckoutFieldValues().get(1);
        String actualOrderCity = productPage.getCheckoutFieldValues().get(2);
        String actualOrderCardNum = productPage.getCheckoutFieldValues().get(3);
        String actualOrderCardMonth = productPage.getCheckoutFieldValues().get(4);
        String actualOrderCardYear = productPage.getCheckoutFieldValues().get(5);

        Object[][] expectedData = getPlaceOrder();
        for (int i = 0; i < expectedData.length; i++) {

            String expectedOrderName = (String) expectedData[i][0];
            String expectedOrderCountry = (String) expectedData[i][1];
            String expectedOrderCity = (String) expectedData[i][2];
            String expectedOrderCardNum = (String) expectedData[i][3];
            String expectedOrderCardMonth = (String) expectedData[i][4];
            String expectedOrderCardYear = (String) expectedData[i][5];


            // Now compare actual with expected (entered data and data before submission).
            Assert.assertEquals(actualOrderName, expectedOrderName, "Order Name mismatch");
            Assert.assertEquals(actualOrderCountry, expectedOrderCountry, "Order Country mismatch");
            Assert.assertEquals(actualOrderCity, expectedOrderCity, "Order City mismatch");
            Assert.assertEquals(actualOrderCardNum, expectedOrderCardNum, "Order Credit Card Number mismatch");
            Assert.assertEquals(actualOrderCardMonth, expectedOrderCardMonth, "Order Credit Card Month mismatch");
            Assert.assertEquals(actualOrderCardYear, expectedOrderCardYear, "Order Credit Card Year mismatch");


        }

    }

    @Test(priority = 4)
    public void tc_04_handlePopupConfirmationValidateOrderDetails() throws IOException {

        //Click Purchase and handle the order confirmation pop-up.
        productPage.clickPurchaseOrderBtn();
        Assert.assertTrue(productPage.isOrderConfirmationPopUpVisible(), " Order Confirmation PopUp is Not Visible");

        // Extract The Data that the pop-up displayed it , as order ID, amount, and purchase details.
        System.out.println("The Data that the pop-up displayed itLog Data Entered :");
        System.out.println("Id is : " + productPage.getId());
        System.out.println("Amount of : " + productPage.getAmount());
        System.out.println("Card Number : " + productPage.getCardNumber());
        System.out.println("name is  : " + productPage.getName());
        System.out.println("Day is  : " + productPage.getDay());
        System.out.println("Month is  : " + productPage.getMonth());
        System.out.println("Year is  : " + productPage.getYear());


        Object[][] expectedData = getPlaceOrder();
        for (int i = 0; i < expectedData.length; i++) {

            String expectedOrderName = (String) expectedData[i][0];
            String expectedOrderCardNum = (String) expectedData[i][3];

            //Validate that the pop-up displays the correct order ID, amount, and purchase details.
            Assert.assertTrue(productPage.getId().matches("\\d+"), "Order ID should be a numeric value");
            Assert.assertEquals(productPage.getAmount(), productPrice, "The Order Amount in the Confirmation Page is Mismatch");
            Assert.assertEquals(productPage.getCardNumber(), expectedOrderCardNum, "The Order credit Card Number in the Confirmation Page is Mismatch");
            Assert.assertEquals(productPage.getName(), expectedOrderName, "The Order Name in the Confirmation Page is Mismatch");
        }

        // Take a screenshot of the confirmation pop-up for record-keeping and save it
        productPage.screenshotOfConfirmationPopup();


    }


}
