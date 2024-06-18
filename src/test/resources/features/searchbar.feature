Feature: APEX - Liverpool - Searchbar

  Background: visit the Liverpool website
    Given I navigate to "https://www.liverpool.com.mx/tienda/home"

    Scenario: Search for a product using searchbar
      Given I enter "smart tv" in the searchbar
      When I click on the search button
      Then I should see the search results page

    Scenario: Search for a non-existent product using searchbar
      Given I enter "asdasd" in the searchbar
      When I click on the search button
      Then I should see the search results page with no results