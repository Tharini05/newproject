@search

Feature: Search
  As an end user
  I want to search a product
  So that i can view product i wish
  Scenario:
    Given Iam in home page
    When I search for product nike
    Then I should see the nike product

 Scenario:Add to basket
    Given Iam in home page
    When I search for product nike
    And It should randomly select one product
    Then Add it to trolley
    And Check the selected page is in trolley page

  Scenario: Select Multiple
    Given Iam in home page
    When I search for product nike
    And It should randomly select one product
    And Select Multiple product
    Then Add it to trolley
    And Get the actual and expected result

  Scenario: Continue Shopping
    Given Iam in home page
    When I search for first product and randomly added to trolley
    And Continue shopping for next product
    And I search for second product and randomly added to trolley
    Then Go to trolley
    And Get the actual and expected result for continue shopping


