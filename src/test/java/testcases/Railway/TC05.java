package testcases.Railway;

import common.Constant.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.Railway.HomePage;
import pageObjects.Railway.LoginPage;

public class TC05 {
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
    public void TC05() {
        System.out.println("System shows message when user enters wrong password several times");
        HomePage homePage = new HomePage().open();

        LoginPage loginPage = homePage.gotoLoginPage();

        for (int i = 1; i <=3; i++) {
            loginPage.login(Constant.USERNAME,"hahahaha");
            loginPage.clearForm();
        }
        String actualMsg = loginPage.login(Constant.USERNAME,"hahaha").getErrorPassMsg();
        String expectedMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
        Assert.assertEquals(actualMsg,expectedMsg,"Error message is not displayed as expected.");

    }
}
