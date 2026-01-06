Feature: Checkout functionality

  Background:
    Given user is logged in
    And user has products in cart

  Scenario: Complete checkout successfully
    When user navigates to cart
    And user proceeds to checkout
    And user enters checkout details:
      | firstName | John   |
      | lastName  | Doe    |
      | zipCode   | 110001 |
    And user completes checkout
    Then order confirmation page should be displayed
