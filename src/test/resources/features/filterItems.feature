Feature: APEX - Liverpool - Filter items

  Background: visit the Liverpool website
    Given I navigate to "https://www.liverpool.com.mx/tienda/home"
    When I enter "smart tv" in the searchbar
    And I click on the search button
    Then I should see the search results page

    Scenario: Filter items by brand
      Given I am on the search results page for "smart tv"
      When I filter the items by price from "0" to "10000"
      And I filter the items by the brand "Samsung"
      And I filter the items by size "50"
      Then I should see the filtered items "Samsung" with size "50" and price from "0" to "10000"