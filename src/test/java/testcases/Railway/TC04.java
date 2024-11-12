package testcases.Railway;

import common.Constant.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.Railway.HomePage;

public class TC04 {
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
    public void TC04() {
        System.out.println("Login page displays when un-logged User clicks on 'Book ticket' tab");
        HomePage homePage = new HomePage().open();

        String actualMsg = homePage.goToBookTicket().isLoginPage();
        String expectedMsg = "Login Page";
        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected.");
    }
}
