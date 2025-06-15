package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

/*
Data is valid - Login success - Test Pass - Logout
Data is valid - Login fail - Test fail

Data is invalid - Login success -  Test fail - Logout
Data is invalid - Login fail - Test Pass
*/

public class TC003_LoginDDT extends BaseClass{
@Test(dataProvider = "LoginExcelData",dataProviderClass= utilities.DataProviders.class,groups="Datadriven")
    public void testlogin(String email, String password, String validity){

    try {
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = new LoginPage(getDriver());
        MyAccountPage myAccountPage = new MyAccountPage(getDriver());
        homePage.clickMyAccount();
        homePage.clickLogin();
        loginPage.enterEmailAddress(email);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        boolean loginStatus = myAccountPage.checkMyAccountPageExists();

    /*

    if(validity.equalsIgnoreCase("Valid") && loginStatus == true){
        logger.info("Test Data is valid and able to login, hence test is passed");
        myAccountPage.clickLogout();
        Assert.assertTrue(true);
    }
    else if(validity.equalsIgnoreCase("Valid") && loginStatus == false)
    {   logger.info("Test Data is valid but unable to login, hence test is failed");
      //  Assert.fail("Test Data is valid but unable to login");
        Assert.assertTrue(false);
    }
    else if(validity.equalsIgnoreCase("Invalid") && loginStatus == true){
        myAccountPage.clickLogout();
        logger.info("Test data is invalid but logged in, hence test is failed");
      //  Assert.fail("Test data is invalid but logged in");
        Assert.assertTrue(false);
    }
    else if(validity.equalsIgnoreCase("Invalid") && loginStatus == false){
        logger.info("Test data is invalid so couldn't logged in, hence test is passed");
        Assert.assertTrue(true);

    }
    else{
        logger.info("Out of scope.................********##########3");
    }
*/
        System.out.println("Status&&&&&&&&&&&&&&&&&& : " + loginStatus);
        if (validity.equalsIgnoreCase("Valid")) {
            if (loginStatus == true) {
                logger.info("Test Data is valid and able to login, hence test is passed");
                myAccountPage.clickLogout();
                Assert.assertTrue(true);
            } else {
                logger.info("Test Data is valid but unable to login, hence test is failed");
                //  Assert.fail("Test Data is valid but unable to login");
                Assert.assertTrue(false);
            }
        }
        if (validity.equalsIgnoreCase("Invalid")) {
            if (loginStatus == true) {
                myAccountPage.clickLogout();
                logger.info("Test data is invalid but logged in, hence test is failed");
                //  Assert.fail("Test data is invalid but logged in");
                Assert.assertTrue(false);
            } else {
                logger.info("Test data is invalid so couldn't logged in, hence test is passed");
                Assert.assertTrue(true);
            }
        }

        //   myAccountPage.clickLogout();
    }
    catch (Exception e)
    {
        Assert.fail(e.getMessage());
    }
    }
}
