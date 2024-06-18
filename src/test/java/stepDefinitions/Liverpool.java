package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;
import pages.StorePage;
import utils.Helpers;

import static hooks.BaseTest.driver;

public class Liverpool {
    HomePage homePage = new HomePage(driver);
    StorePage storePage = new StorePage(driver);

    @Given("I navigate to {string}")
    public void i_navigate_to(String url) {
        driver.get(url);
    }

    @When("I enter {string} in the searchbar")
    public void i_enter_in_the_searchbar(String itemToSearch) {
        homePage.searchItem(itemToSearch);
        Helpers.takeScreenShot();
    }

    @When("I click on the search button")
    public void i_click_on_the_search_button() {
        homePage.clickSearchButton();
        Helpers.takeScreenShot();
    }

    @Then("I should see the search results page")
    public void i_should_see_the_search_results_page() {
        storePage.checkSearchResults();
        Helpers.takeScreenShot();
    }

    @Given("I am on the search results page for {string}")
    public void i_am_on_the_search_results_page_for(String search) {
        Assert.assertTrue(driver.getTitle().contains(search));
        Helpers.takeScreenShot();
    }

    @When("I filter the items by the brand {string}")
    public void i_filter_the_items_by_the_brand(String brandName) throws InterruptedException {
        Thread.sleep(3000);
        storePage.filterByBrand(brandName);
        Helpers.takeScreenShot();
    }

    @When("I filter the items by size {string}")
    public void i_filter_the_items_by_size(String size) throws InterruptedException {
        Thread.sleep(3000);
        storePage.filterBySize(size);
        Helpers.takeScreenShot();
    }

    @When("I filter the items by price from {string} to {string}")
    public void i_filter_the_items_by_price_from_to(String minPrice, String maxPrice) throws InterruptedException {
        Thread.sleep(3000);
        storePage.filterByPriceRange(minPrice, maxPrice);
        Helpers.takeScreenShot();
    }

    @Then("I should see the filtered items {string} with size {string} and price from {string} to {string}")
    public void i_should_see_the_filtered_items(String brand, String size, String minPrice, String maxPrice) throws InterruptedException {
        Thread.sleep(5000);
        storePage.assertItemsDescription(brand, size, Integer.parseInt(minPrice), Integer.parseInt(maxPrice));
        Helpers.takeScreenShot();
    }

    @Then("I should see the search results page with no results")
    public void i_should_see_the_search_results_page_with_no_results() {
        storePage.checkItemNotFound();
        Helpers.takeScreenShot();
    }

}
