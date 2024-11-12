package testcases.Railway;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Railway.HomePage;
import pageObjects.Railway.RegisterPage;

public class TestCase11 extends PreparationCommonTest {

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
