package testcases.Railway;

import common.Constant.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.Railway.ChangePassword;
import pageObjects.Railway.HomePage;
import pageObjects.Railway.LoginPage;

public class TC09 {
    @BeforeMethod
    public void beforMethod() {
        System.out.println("Pre-condition");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("Post-condition");

        Constant.WEBDRIVER.quit();
    }
    @Test
    public void TC09() {
        System.out.println("User can change password");
        HomePage homePage = new HomePage().open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME,Constant.PASSWORD);

        ChangePassword changePassword  = loginPage.goToChangePassWord();
        String actualMsg = changePassword.changePassword("0987654321","0987654321","0987654321").getChangePasswordMsg();
        String expectedMsg =  "Your password has been updated!";
        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected.");
    }
}
