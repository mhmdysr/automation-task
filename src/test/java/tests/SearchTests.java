package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pages.SearchPage;

public class SearchTests extends BaseTest {

    //The Website doesn't have a search bar, so instead, we check if a specific product ("Nexus 6") exists on the homepage.
    //I'am simulate a search by scanning product cards on the homepage and performing a case-insensitive match.


    SearchPage searchPage;
    @Test(priority = 1)
    public void searchAndValidateProduct()  {

        searchPage=new SearchPage(driver);

        //String expectedProduct = "Samsung galaxy s6";
        String expectedProduct = "NeXUs 6"; // to check Case-insensitive.
        boolean isFound = searchPage.isProductDisplayed(expectedProduct);
        Assert.assertTrue(isFound, "Product not found on page: " + expectedProduct);



    }

    @Test(priority = 2)
    public void testSearchForNonExistentProduct()  {

        searchPage=new SearchPage(driver);

        //String expectedProduct = "Samsung galaxy s6";
        String expectedProduct = "IPHone 16 pro Maxx"; // to check Case-insensitive.
        boolean isFound = searchPage.isProductDisplayed(expectedProduct);
        Assert.assertFalse(isFound, "Product not found on page: " + expectedProduct);



    }



}
