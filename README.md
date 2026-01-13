🚀 Selenium BDD Framework (Cucumber + TestNG)

This project is a UI Automation Framework built using:

Selenium WebDriver

BDD Cucumber

TestNG

Page Object Model (POM)

Maven

Log4j

WebDriverManager

It automates the Login, Product browsing, Cart management, Checkout, and Logout functionalities of SauceDemo — a sample e-commerce website used for testing automation.

==========================================================================
📁 Project Structure

Automation-BDD-Project
│
├── .gitignore
├── pom.xml
├── README.md
├── testng.xml
├── .vscode/
├── Configuration/
│   └── config.properties
│
└── src/
    └── test/
        ├── java/
        │   ├── BaseClass/
        │   │   └── baseClass.java
        │   │
        │   ├── Hooks/
        │   │   └── hooks.java
        │   │
        │   ├── PageObject/
        │   │   ├── CartPage.java
        │   │   ├── CheckOutPage.java
        │   │   ├── LoginPage.java
        │   │   └── ProductPage.java
        │   │
        │   ├── resources/
        │   │   ├── cart.feature
        │   │   ├── checkout.feature
        │   │   ├── login.feature
        │   │   ├── logout.feature
        │   │   └── product.feature
        │   │
        │   ├── Runners/
        │   │   └── runnerTest.java
        │   │
        │   ├── StepDefinition/
        │   │   ├── CartStep.java
        │   │   ├── CheckOutStep.java
        │   │   ├── LoginStep.java
        │   │   ├── LogoutStep.java
        │   │   └── ProductStep.java
        │   │
        │   └── utilities/
        │       ├── AnnotationTransformer.java
        │       ├── configReader.java
        │       ├── ExtentManager.java
        │       ├── RetryAnalyzer.java
        │       ├── TestContext.java
        │       └── TestNGListener.java
        │
        └── resources/
            └── log4j2.xml

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

3️⃣ Run tests
- All features: `mvn clean test`
- Specific features: Modify `features` in `runnerTest.java`
- With tags: Add `tags = "@tag"` in `runnerTest.java`

4️⃣ Reports
- Cucumber HTML: `target/cucumber-reports/cucumber.html`
- ExtentReports: `target/extent-reports/`

==========================================================================
🛠️ config.properties

url=https://www.saucedemo.com
username=standard_user
password=secret_sauce
browser=chrome

==========================================================================
🧬 Feature File Examples

**login.feature**
```
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
```

**product.feature**
```
@valid
Feature: Products page functionality

   Background:
     Given user is logged in

   Scenario: Verify products page is displayed
     Then products page should be displayed

   Scenario Outline: Sort products
     When user sorts products by "<sortOption>"
     Then products should be sorted accordingly

     Examples:
       | sortOption          |
       | Name (A to Z)       |
       | Price (low to high) |
```

**cart.feature**
```
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
```

**checkout.feature**
```
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
```

**logout.feature**
```
Feature: Logout functionality

   Background:
     Given user is logged in

   Scenario: Logout from application
     When user logs out
     Then user should be redirected to login page
```
==========================================================================

🧑‍💻 Author

Pawan Singh
SDET | Automation Tester
GitHub: your-profile-link


