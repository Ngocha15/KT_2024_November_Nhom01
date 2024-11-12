package testcases.Railway;

import org.testng.annotations.Test;
import pageObjects.Railway.ForgotPassword;
import pageObjects.Railway.HomePage;
import pageObjects.Railway.LoginPage;

public class TestCase12 extends PreparationCommonTest {

    @Test
    public void TC12() {
        System.out.println("Errors display when password reset token is blank");
        HomePage homePage = new HomePage().open();
        LoginPage loginPage = homePage.gotoLoginPage();
        ForgotPassword forgotPassword = loginPage.goToForgotPasswordPage();
        forgotPassword.forgotPassword("haha@gmail.com");
    }
}
