Feature: MagicBricks Website Automation

  # Common setup for all scenarios
  Background:
    Given User navigates to "https://www.magicbricks.com"
    And User verifies that the MagicBricks homepage is loaded successfully

  # ---------------------- Scenario 1 ----------------------
  @SearchTags @PrintHTML
  Scenario: Verify search and print all HTML tag names
    Given I am on the Home Page
    And User checks the homepage 
    When User clicks on the Search button
    Then Search results page should be displayed
    And User prints all unique HTML tag names on the search results page

  # ---------------------- Scenario 2 ----------------------
  @EMICalculator @ScenarioOutline
Scenario Outline: EMI Calculator using different data sets
  Given I am on the Home Page
  When I enter loan amount "<LoanAmount>" and interest rate "<InterestRate>"

Examples:
  | LoanAmount | InterestRate |
  | 500000     | 7.5          |
  | 1000000    | 8.0          |

  # ---------------------- Scenario 3 ----------------------
  @PropertyListing @SortByPrice
  Scenario: View all properties and sort by price low to high
    Given I am on the Home Page
    When I click on the "See All Properties" tab
    Then I should be redirected to the Property Listings page
    When I select "Price: Low to High" from the sort by dropdown

  # ---------------------- Scenario 4 ----------------------
  @InteriorEstimator @NegativeTest
  Scenario: Interior Budget Estimator negative test
    Given I am on the Home Page
    When I click on "Interior Budget Estimator"
    And I submit the estimator form without filling any details
    Then I should see appropriate validation error messages