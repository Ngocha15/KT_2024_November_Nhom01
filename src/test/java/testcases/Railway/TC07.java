package testcases.Railway;

import common.Constant.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.Railway.HomePage;
import pageObjects.Railway.RegisterPage;

public class TC07 {
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
    public void TC07() {
        System.out.println("User can create new account");

        HomePage homePage = new HomePage().open();

        RegisterPage registerPage = homePage.gotoRegisterPage();

        String actualMsg =  registerPage.register("gaugauu@gmail.com","123456789","123456789","123456789").getWelcomeRegisterMsg();
        String expectedMsg =  "Thank you for registering your account.";
        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected.");
    }
}
