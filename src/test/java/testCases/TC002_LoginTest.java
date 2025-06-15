package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest extends BaseClass{
@Test(groups= {"Sanity","Master"})
public void testLogin() {
    try {
        logger.info("*********Starting TC002_LoginTest***********");
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        MyAccountPage myAccountPage = new MyAccountPage(getDriver());
        homePage.clickMyAccount();
        logger.info("Clicked on My Account link");
        homePage.clickLogin();
        loginPage.enterEmailAddress(TC001_AccountRegistrationTest.emailAddress);
        logger.info("Email Id Entered");
        logger.info("*********Entering email address*******"+ TC001_AccountRegistrationTest.emailAddress);
        loginPage.enterPassword(TC001_AccountRegistrationTest.password);
        logger.info("*********Entering password********");
        loginPage.clickLogin();
        logger.info("*******clicked on Login button*********");
        Assert.assertTrue(myAccountPage.checkMyAccountPageExists(),"Login Failed.......");
        myAccountPage.clickLogout();
    //    Thread.sleep(2000);
    } catch (Exception e) {
        logger.error("Test failed :"+e);
        Assert.fail(e.getMessage());
    }
    logger.info("*******TC002_LoginTest finished**********");
}

}
