package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyAccountPage extends BasePage {

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

   @FindBy(xpath = "//h2[text()='My Account']")
   WebElement myAccountHeader;

@FindBy(xpath = "(//a[text()='Logout'])[2]")
WebElement logoutBtn;

    public boolean checkMyAccountPageExists() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOf(myAccountHeader));
            return myAccountHeader.isDisplayed();
        } catch (Exception e) {
            System.out.println("******Exception is*********:::::::::::::::" +e);
            return false;
        }
    }

    public void clickLogout(){
        logoutBtn.click();
    }
}