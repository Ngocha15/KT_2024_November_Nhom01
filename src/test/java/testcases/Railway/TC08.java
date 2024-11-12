package testcases.Railway;

import common.Constant.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.Railway.HomePage;
import pageObjects.Railway.LoginPage;

public class TC08 {
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
    public void TC08() {
        System.out.println("User can't login with an account hasn't been activated");
        HomePage homePage = new HomePage().open();

        LoginPage loginPage = homePage.gotoLoginPage();
        String actualMsg = loginPage.login(Constant.USERNAME,Constant.PASSWORD).getWelcomeMessage();
        String expectedMsg =  "Invalid username or password. Please try again.";
        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected.");
    }
}
