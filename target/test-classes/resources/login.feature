Feature: Login functionality

  Scenario Outline: Login with credentials
    Given user is on login page
    When user enters username "<username>"
    And user enters password "<password>"
    And user clicks login button
    Then user should navigate to "<expected>"

    Examples:
      | username      | password     | expected            |
      | standard_user | secret_sauce | home page           |
      | invalid_user  | wrong_pass   | error message       |
      |               | secret_sauce | username required   |
      | standard_user |              | password required   |
