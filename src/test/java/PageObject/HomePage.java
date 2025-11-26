package PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

     public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    By productsTitle = By.xpath("//span[text()='Products']");
  public boolean isProductsDisplayed() {
        return driver.findElement(productsTitle).isDisplayed();
    }
}
