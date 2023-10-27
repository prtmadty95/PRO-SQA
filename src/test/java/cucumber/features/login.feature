Feature: Login Aplikasi swag labs
  Scenario: Login with valid credential
    Given login page swage labs
    When user input correct username
    And user input correct password
    And user click login button
    Then User navigated to products page
  Scenario: Login with wrong credential
    Given login page swage labs
    When user input correct username
    And user input invalid password
    And user click login button
    Then User get error message