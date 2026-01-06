Feature: Cart functionality

  Background:
    Given user is logged in

  Scenario: Add product to cart
    When user adds "Sauce Labs Backpack" to cart
    Then cart badge count should be "1"

  Scenario: Remove product from cart
    Given user has product in cart
    When user removes product from cart
    Then cart should be empty

  Scenario: Navigate to cart page
    When user opens cart
    Then cart page should be displayed
