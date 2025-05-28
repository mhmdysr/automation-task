package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductPage;
import pages.SearchPage;

import static org.apache.commons.exec.util.DebugUtils.handleException;

public class CheckoutErrorHandlingTests extends BaseTest {
    ProductPage productPage;

    @Test(priority = 1)
    public void addProductToCart() {
        productPage = new ProductPage(driver);

        productPage.clickNokiaProduct();
        productPage.clickAddToCartBtn();
        productPage.handeAlertPopup("accept");
        productPage.clickCartBtn();
        productPage.clickPlaceOrderBtn();
    }

    @Test(priority = 2)
    public void tc_01_testEmptyCheckoutFields() {
        productPage.fillCheckoutDetails("", "", "", "", "", "");
        productPage.clickPurchaseOrderBtn();
        String actualErrorMsgFieldsEmpty = productPage.errorMsPopUp();

        //1-verify that Fields are left empty during checkout.
        Assert.assertEquals(actualErrorMsgFieldsEmpty, "Please fill out Name and Creditcard.", "Fields are Not left empty during checkout.");


    }

    @Test(priority = 3)
    public void tc_02_testInvalidCreditCard() {
        productPage.fillCheckoutDetails("User's Order", "KSA", "Makkah", "#%/*", "11", "2027");
        productPage.clickPurchaseOrderBtn();

        try {
            // Validate error message
            Assert.assertTrue(productPage.errorMsPopUp().contains("Credit card format is invalid"),
                    "Invalid card error not displayed. Actual: " + productPage.errorMsPopUp());

        } catch (Exception e) {
            handleException("Invalid credit card test failed", e);
            System.out.println("  handled unexpected error - " + e.getMessage());

        }


    }

    @Test(priority = 3)
    public void tc_04_testSearchNoResults() {

        SearchPage searchPage = new SearchPage(driver);


        try {
            // Validate Element not found
            String expectedProduct = "IPHone 16 pro Max"; // to check Case-insensitive.
            boolean isFound = searchPage.isProductDisplayed(expectedProduct);
            Assert.assertFalse(isFound, "Product not found on page: " + expectedProduct);


        } catch (Exception e) {
            handleException("Product  found on page", e);
            System.out.println("  handled unexpected error - " + e.getMessage());
        }


    }

}
