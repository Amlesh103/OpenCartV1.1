/*

package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

    public AccountRegistrationPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id="input-firstname")
    private WebElement firstNameTextbox;

    @FindBy(id="input-lastname")
    private WebElement lastnameTextbox;

    @FindBy(id= "input-email")
    private WebElement emailTextbox;

    @FindBy(id= "input-telephone")
    private WebElement phoneTextbox;

    @FindBy(id= "input-password")
    private WebElement passwordTextbox;

    @FindBy(id ="input-confirm")
    private WebElement confirmPasswordTextbox;

    @FindBy(xpath="//input[@name='agree']")
    private WebElement privacyPolicyCheckBox;

    @FindBy(xpath="//input[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@id='content']//h1")
    private WebElement confirmationMessage;

    public void enterFirstname(String firstname){
        firstNameTextbox.sendKeys(firstname);
    }

    public void enterLastname(String lastname){
        lastnameTextbox.sendKeys(lastname);
    }

    public void enterEmail(String email){
        emailTextbox.sendKeys(email);
    }

    public void enterTelephone(String tel){ phoneTextbox.sendKeys(tel);}

    public void enterPassword(String password) {

        passwordTextbox.sendKeys(password);
    }
    public void enterConfirmPassword(String password)
    {
        confirmPasswordTextbox.sendKeys(password);
    }

    public void clickOnPrivacyPolicyButton(){
        privacyPolicyCheckBox.click();
    }

    public void submitButton(){
        submitButton.click();
    }

    public String confirmOnSubmission(){
       return confirmationMessage.getText();
    }

}
*/

package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

    public AccountRegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "input-firstname")
    private WebElement firstNameTextbox;

    @FindBy(id = "input-lastname")
    private WebElement lastnameTextbox;

    @FindBy(id = "input-email")
    private WebElement emailTextbox;

    @FindBy(id = "input-telephone")
    private WebElement phoneTextbox;

    @FindBy(id = "input-password")
    private WebElement passwordTextbox;

    @FindBy(id = "input-confirm")
    private WebElement confirmPasswordTextbox;

    @FindBy(xpath = "//input[@name='agree']")
    private WebElement privacyPolicyCheckBox;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@id='content']//h1")
    private WebElement confirmationMessage;

    public void enterFirstname(String firstname) {
        firstNameTextbox.sendKeys(firstname);
    }

    public void enterLastname(String lastname) {
        lastnameTextbox.sendKeys(lastname);
    }

    public void enterEmail(String email) {
        emailTextbox.sendKeys(email);
    }

    public void enterTelephone(String tel) {
        phoneTextbox.sendKeys(tel);
    }

    public void enterPassword(String password) {
        passwordTextbox.sendKeys(password);
    }

    public void enterConfirmPassword(String password) {
        confirmPasswordTextbox.sendKeys(password);
    }

    public void clickOnPrivacyPolicyButton() {
        privacyPolicyCheckBox.click();
    }

    public void clickSubmit() {
        submitButton.click();
    }

    public String confirmOnSubmission() {
        return confirmationMessage.getText();
    }
}