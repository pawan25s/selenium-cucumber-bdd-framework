🚀 Selenium BDD Framework (Cucumber + TestNG)

This project is a UI Automation Framework built using:

Selenium WebDriver

BDD Cucumber

TestNG

Page Object Model (POM)

Maven

Log4j

WebDriverManager

It automates the Login functionality of SauceDemo — a sample e-commerce website used for testing automation.

==========================================================================
📁 Project Structure

Automation-BDD-Project
│
├── pom.xml
├── config.properties
│
└── src/test/java
    ├── BaseClass/
    │   └── baseClass.java
    │
    ├── StepDefinition/
    │   └── LoginStep.java
    │
    ├── PageObject/
    │   └── LoginPage.java
    │
    ├── Runners/
    │   └── runnerTest.java
    │
    └── resources/
        └── login.feature

⚙️ Technologies Used

| Component       | Technology              |
| --------------- | ----------------------- |
| Language        | Java                    |
| Test Runner     | TestNG + Cucumber       |
| UI Automation   | Selenium WebDriver      |
| Build Tool      | Maven                   |
| BDD             | Cucumber                |
| Design Pattern  | Page Object Model (POM) |
| Logging         | Log4j                   |
| Browser Drivers | WebDriverManager        |

========================================================================

🧩 How Framework Works (Flow)

Feature → Runner → Step Definition → BaseClass → Page Objects → Browser → Validation

1️⃣ Runner class

=>Executes Cucumber feature files
=>Calls step definitions

2️⃣ Step Definitions

=>Contains automation steps
=>Access values from config.properties
=>Calls page methods

3️⃣ BaseClass

=>Reads config file
=>Creates WebDriver
=>Provides getProperty() method

4️⃣ Page Object

=>Contains locators & actions
=>Example: setUsername(), enterPassword(), clickLogin()

5️⃣ Browser Execution

=>Selenium performs actions

6️⃣ Assertions & Logs

=>TestNG asserts validate the result
=>Log4j prints execution logs

==========================================================================
🧪 How to Run Tests
1️⃣ Clone the project
git clone <repo-url>

2️⃣ Navigate to project
cd Automation-BDD-Project

3️⃣ Run test
mvn clean test

4️⃣ Cucumber report (Optional)
/target/cucumber-reports

==========================================================================
🛠️ config.properties

url=https://www.saucedemo.com
username=standard_user
password=secret_sauce
browser=chrome

==========================================================================
🧬 Feature File Example (login.feature)

Feature: Login functionality

  Scenario: Valid login
    Given user is on login page
    When user enters username
    And user enters password
    And user clicks login button
    Then user should navigate to home page
==========================================================================

🧑‍💻 Author

Pawan Singh
SDET | Automation Tester
GitHub: your-profile-link


