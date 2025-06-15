/*
package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

public HomePage(WebDriver driver){
    super(driver);
}

//@FindBy(xpath= "//span[text()='My Account']")
//@FindBy(xpath="//span[normalize-space()='My Account']")
@FindBy(xpath="//a[@title='My Account']")
 WebElement myAccountLink;

//@FindBy(xpath = "//a[text()='Register']")
@FindBy(xpath="//a[normalize-space()='Register']")
 WebElement registerLink;

@FindBy(xpath= "//a[text()='Login']")
    private WebElement loginLink;

    public void clickMyAccount(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='My Account']")));
        wait.until(ExpectedConditions.visibilityOf(myAccountLink));
        wait.until(ExpectedConditions.elementToBeClickable(myAccountLink));

        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(myAccountLink).click().perform();
        } catch (Exception e) {
            // Fallback to JS click
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", myAccountLink);
        }

        System.out.println("Clicked My Account");
    }


public void clickRegister(){
    registerLink.click();
}

public void clickLogin(){
    loginLink.click();
}

}
*/

package pageObjects;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigReader;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath="//a[@title='My Account']")
    WebElement myAccountLink;

    @FindBy(xpath="//a[normalize-space()='Register']")
    WebElement registerLink;

    @FindBy(xpath= "//a[text()='Login']")
    private WebElement loginLink;

    public void clickMyAccount() throws IOException {
        try {
            // Log page context for debugging
            System.out.println("Page title: " + driver.getTitle());
            System.out.println("Page URL: " + driver.getCurrentUrl());
            System.out.println("ConfigReader.readURL(): " + ConfigReader.readURL());

            // Save screenshot
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "/screenshots/myaccount_debug.png"));

            // Wait until page is fully loaded
            new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(webDriver -> ((JavascriptExecutor) webDriver)
                            .executeScript("return document.readyState").equals("complete"));

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='My Account']")));
            wait.until(ExpectedConditions.visibilityOf(myAccountLink));
            wait.until(ExpectedConditions.elementToBeClickable(myAccountLink));

            try {
                Actions actions = new Actions(driver);
                actions.moveToElement(myAccountLink).click().perform();
            } catch (Exception e) {
                // Fallback to JS click
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", myAccountLink);
            }

            System.out.println("Clicked My Account");
        } catch (Exception e) {
            System.err.println("clickMyAccount failed: " + e.getMessage());
            try {
                File failureShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(failureShot, new File(System.getProperty("user.dir") + "/screenshots/myaccount_error.png"));
            } catch (IOException ioException) {
                System.err.println("Failed to capture screenshot on error.");
            }
            throw e;
        }
    }

    public void clickRegister(){
        registerLink.click();
    }

    public void clickLogin(){
        loginLink.click();
    }
}
