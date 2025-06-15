package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id="input-email")
    WebElement emailAddressBox;

    @FindBy(id="input-password")
    WebElement passwordBox;

    @FindBy(xpath="//input[@value='Login']")
    WebElement loginBtn;

    public void enterEmailAddress(String email){
        emailAddressBox.sendKeys(email);
    }

    public void enterPassword(String password){
        passwordBox.sendKeys(password);
    }

    public void clickLogin(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        wait.until(ExpectedConditions.visibilityOf(loginBtn)).click();
   //     loginBtn.click();
    }


}
