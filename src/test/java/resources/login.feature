Feature: Login functionality

  Background:
    Given user is on the SauceDemo login page

  Scenario Outline: Login with different credentials
    When user enters username "<username>"
    And user enters password "<password>"
    And user clicks login button
    Then login result should be "<expected>"

    Examples:
      | username      | password     | expected             |
      | standard_user | secret_sauce | success               |
      | invalid_user  | wrong_pass   | invalid credentials   |
      |               | secret_sauce | username required     |
      | standard_user |              | password required     |

