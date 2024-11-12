package testcases.Railway;

import common.Constant.Constant;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Railway.HomePage;
import pageObjects.Railway.LoginPage;

public class TestCase01 extends PreparationCommonTest{
    @Test
    public void TC01() {
        System.out.println("TC01 - User can log into Railway with valid username and password");

        HomePage homePage = new HomePage().open();
        LoginPage loginPage = homePage.gotoLoginPage();

        String actualMsg = loginPage.login(Constant.USERNAME, Constant.PASSWORD).getWelcomeMessage();
        String expectedMsg = "Welcome " + Constant.USERNAME;
        Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
    }
}
