Feature: Login functionality

  Scenario: Login with valid credentials
    Given user is on login page
    When user enters username
    And user enters password
    And user clicks login button
    Then user should navigate to home page
