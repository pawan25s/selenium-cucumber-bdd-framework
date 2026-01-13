Feature: Login functionality

   Background:
     Given user is on the SauceDemo login page

   @valid
   Scenario: Login with valid credentials
     When user enters username "standard_user"
     And user enters password "secret_sauce"
     And user clicks login button
     Then login result should be "success"

   Scenario Outline: Login with invalid credentials
     When user enters username "<username>"
     And user enters password "<password>"
     And user clicks login button
     Then login result should be "<expected>"

     Examples:
       | username      | password     | expected             |
       | invalid_user  | wrong_pass   | invalid credentials   |
       |               | secret_sauce | username required     |
       | standard_user |              | password required     |

