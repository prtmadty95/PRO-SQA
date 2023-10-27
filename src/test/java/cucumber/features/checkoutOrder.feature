Feature: checking out order
  Scenario: checkout product from cart
    Given Login into swag labs
    When Product add to cart
    And user click product cart
    And user click checkout button
    Then user navigated to checkout information
    And user input checkout information
    And user click continue button
    Then user click finish button
