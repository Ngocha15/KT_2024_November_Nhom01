package testcases.Railway;

import common.Constant.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.Railway.HomePage;
import pageObjects.Railway.RegisterPage;

public class TC11 {
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
    public void TC11() {
        System.out.println("User can't create account while password and PID fields are empty");
        HomePage homePage = new HomePage().open();
        RegisterPage registerPage = homePage.gotoRegisterPage();
        registerPage.register("meomeo@gmail.com","","","");

        Assert.assertEquals(registerPage.getErrorConfirmPasswordMsg(), "There're errors in the form. Please correct the errors and try again.", "Error message is not displayed as expected.");
        Assert.assertEquals(registerPage.getErrorPasswordLengthMsg(), "Invalid password length", "Error message is not displayed as expected.");
        Assert.assertEquals(registerPage.getErrorPidLengthMsg(), "Invalid ID length", "Error message is not displayed as expected.");
    }
}
