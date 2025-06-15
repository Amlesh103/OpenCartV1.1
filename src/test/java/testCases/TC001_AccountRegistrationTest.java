package testCases;

import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass {
  //  public WebDriver driver;
    public Faker faker;
    public static  String emailAddress;
    public static String password;

    @Test(groups= {"Regression","Master"})
    public void verify_account_registration(){
        try {
            HomePage homePage = new HomePage(getDriver());
            logger.info("***********Starting Account Registration Test*****************");
            faker = new Faker();
            homePage.clickMyAccount();
            logger.info("Clicked on My Account Link");
            homePage.clickRegister();
            logger.info("Clicked on Register Link");

            AccountRegistrationPage registerAcc = new AccountRegistrationPage(getDriver());
            logger.info("Providing customer details...");
            registerAcc.enterFirstname(faker.name().firstName());
            logger.info("Entering first name");
            registerAcc.enterLastname(faker.name().lastName());
            logger.info("Entering last name");

            emailAddress = faker.internet().emailAddress();
            registerAcc.enterEmail(emailAddress);
            logger.info("Entering email address" + emailAddress);
            registerAcc.enterTelephone(faker.phoneNumber().cellPhone());
            logger.info("Entering phone number");
            password = faker.internet().password();
            registerAcc.enterPassword(password);
            registerAcc.enterConfirmPassword(password);
            logger.info("Entering password" + password);
            registerAcc.clickOnPrivacyPolicyButton();
            logger.info("Clicked on privacy policy button");
            registerAcc.clickSubmit();
            logger.info("Clicked on submit button");
            String confirmationmsg = registerAcc.confirmOnSubmission();
            System.out.println("Confirmation msg is"+ confirmationmsg);
            if(confirmationmsg.equals("Your Account Has Been Created!")){
                Assert.assertTrue(true);
                logger.info("********Account is created*********");
            }
            else {
                logger.error("Test failed");
                logger.debug("Debug logs...");
                Assert.assertTrue(false);
            }
        //    Assert.assertEquals(registerAcc.confirmOnSubmission(), "Your Account Has Been Created!");
         //   logger.info("Validated assertion message");
        } catch (Exception e) {
           // logger.debug("Debug logs...");

           // logger.error("Test failed");

            logger.error("Test failed", e); // You should log the exception
            Assert.fail("Exception occurred: " + e.getMessage());
        }
        logger.info("**********Finished Account Registration Test*************");


    }
}
