package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.Helpers;

import java.util.List;

public class StorePage {
    WebDriver driver;

    public StorePage(WebDriver driver) {
        this.driver = driver;
    }

    public static String productsListSelector = "ul.m-product__listingPlp";
    public static String numProductsSelector = "p[class='a-plp-results-title'] span";
    public static String itemNotFoundContainerSelector = ".o-content__noResultsNullSearch";
    public static String minPriceInputSelector = "#min-price-filter";
    public static String maxPriceInputSelector = "#max-price-filter";
    public static String filterPriceButtonSelector = ".a-price__filterButton";
    public static String inputSearchBrandSelector = "#searchBrand";
    public static String inputBrandSamsungSelector = "#brand-SAMSUNG";
    public static String inputSize50InchesSelector = "input[id='variants.normalizedSize-# pulgadas']";
    public static String itemDescriptionContainerSelector = ".card-title";
    public static String itemPriceContainerSelector = ".a-card-discount";

    public void checkSearchResults() {
        WebElement productsList = Helpers.waitToBeVisible(By.cssSelector(productsListSelector));
        WebElement numProducts = Helpers.waitToBeVisible(By.cssSelector(numProductsSelector));
        int numProductsInt = Integer.parseInt(numProducts.getText());
        Assert.assertTrue(numProductsInt > 0);
    }

    public void checkItemNotFound() {
        WebElement itemNotFoundContainer = Helpers.waitToBeVisible(By.cssSelector(itemNotFoundContainerSelector));
        Assert.assertTrue(itemNotFoundContainer.isDisplayed());
        Assert.assertTrue(itemNotFoundContainer.getText().contains("Lo sentimos, no encontramos nada para"));
    }

    public void filterByBrand(String brandName) {
        WebElement inputSearchBrand = Helpers.waitToBeVisible(By.cssSelector(inputSearchBrandSelector));
        Helpers.scrollToElement(inputSearchBrand);
        inputSearchBrand.sendKeys(brandName);
        WebElement inputBrandSamsung = Helpers.waitToBeVisible(By.cssSelector(inputBrandSamsungSelector));
        Helpers.scrollToElement(inputBrandSamsung);
        inputBrandSamsung.click();
    }

    public void filterBySize(String size) {
        inputSize50InchesSelector = inputSize50InchesSelector.replace("#", size);
        WebElement inputSize50Inches = Helpers.waitToBeVisible(By.cssSelector(inputSize50InchesSelector));
        inputSize50Inches.click();
    }

    public void filterByPriceRange(String minPrice, String maxPrice) {
        WebElement minPriceInput = Helpers.waitToBeVisible(By.cssSelector(minPriceInputSelector));
        Helpers.scrollToElement(minPriceInput);
        minPriceInput.sendKeys(minPrice);
        WebElement maxPriceInput = Helpers.waitToBeVisible(By.cssSelector(maxPriceInputSelector));
        maxPriceInput.sendKeys(maxPrice);
        WebElement filterPriceButton = Helpers.waitToBeVisible(By.cssSelector(filterPriceButtonSelector));
        filterPriceButton.click();
    }

    public void assertItemsDescription(String brand, String size, int minPrice, int maxPrice) {
        List<WebElement> productList = driver.findElements(By.cssSelector(itemDescriptionContainerSelector));
        for (WebElement product : productList) {
            String productText = product.getText().toLowerCase();
            Assert.assertTrue(productText.contains(brand.toLowerCase()));
        }
        List<WebElement> productPriceList = driver.findElements(By.cssSelector(itemPriceContainerSelector));
        for (WebElement productPrice : productPriceList) {
            String productPriceText = productPrice.getText().replace("$", "").replace(",", "");
            String productPriceWithCents = productPriceText.substring(0, productPriceText.length() - 2) + "." + productPriceText.substring(productPriceText.length() - 2);
            float productPriceInt = Float.parseFloat(productPriceWithCents);
            Assert.assertTrue(productPriceInt >= minPrice && productPriceInt <= maxPrice);
        }
    }
}
