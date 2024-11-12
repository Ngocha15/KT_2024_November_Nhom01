package testcases.Railway;

import common.Constant.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.Railway.HomePage;
import pageObjects.Railway.LoginPage;

public class TestCase02 extends PreparationCommonTest{
    @Test
    public void TC02() {

        System.out.println("TC02 - User can't login with blank 'Username' textbox");
        HomePage homePage= new HomePage().open();
        LoginPage loginPage = homePage.gotoLoginPage();

        String actualMsg = loginPage.login("",Constant.PASSWORD).getErrorMassage();
        String expectedMsg = "There was a problem with your login and/or errors exist in your form.";
        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected.");
    }
}
