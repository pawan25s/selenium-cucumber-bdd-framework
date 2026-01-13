
@valid
Feature: Products page functionality

  Background:
    Given user is logged in

  # -------- Page Validation --------

  Scenario: Verify products page is displayed
    Then products page should be displayed

  Scenario: Verify products page title
    Then products page title should be "Products"

  Scenario: Verify product list is not empty
    Then product list should contain at least one product

  # -------- Product Details --------

  Scenario: Verify each product has name, price and image
    Then each product should display name, price and image

  Scenario: Verify product price format
    Then product prices should be displayed in valid currency format

  # -------- Sorting --------

  Scenario Outline: Sort products
    When user sorts products by "<sortOption>"
    Then products should be sorted accordingly

    Examples:
      | sortOption          |
      | Name (A to Z)       |
      | Name (Z to A)       |
      | Price (low to high) |
      | Price (high to low) |

  # -------- Product Details Page --------

  Scenario: Open product detail page
    When user opens product "Sauce Labs Backpack"
    Then product detail page should be displayed

  Scenario: Navigate back to products page
    Given user is on product detail page
    When user clicks back to products
    Then products page should be displayed
