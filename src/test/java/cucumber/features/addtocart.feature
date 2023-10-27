Feature: Add to cart
  Scenario: Add product to cart
    Given Login into the swag aplication
    When All Products page swag
    And user click product
    And user click Add to Cart button
    Then product added to user cart