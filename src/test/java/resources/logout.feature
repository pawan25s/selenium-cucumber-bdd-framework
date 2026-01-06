Feature: Logout functionality

  Background:
    Given user is logged in

  Scenario: Logout from application
    When user logs out
    Then user should be redirected to login page
