package testcases.Railway;

import org.testng.annotations.Test;
import pageObjects.Railway.ForgotPassword;
import pageObjects.Railway.HomePage;
import pageObjects.Railway.LoginPage;

public class TestCase13 extends PreparationCommonTest {

    @Test
    public void TC13() {
        System.out.println("Errors display if password and confirm password don't match when resetting password");
        HomePage homePage = new HomePage().open();
        LoginPage loginPage = homePage.gotoLoginPage();
        ForgotPassword forgotPassword = loginPage.goToForgotPasswordPage();
        forgotPassword.forgotPassword("haha@gmail.com");
    }
}
