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
import pageObjects.Railway.MyTicket;

//Thêm bỏ qua test
public class TC06 {
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
    public void TC06() {
        System.out.println("Additional pages display once user logged in");
        HomePage homePage = new HomePage().open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME,Constant.PASSWORD);

        Assert.assertEquals(loginPage.getTicketPriceTabText(), "Ticket price", "Ticket tab price not display");
        Assert.assertEquals(loginPage.getBookTicketTabText(), "Book ticket", "Book ticket tab not display");
        Assert.assertEquals(loginPage.getLogoutTabText(), "Logout", "logout tab not display");
        MyTicket myTicket = loginPage.goToMyTicket();
        Assert.assertEquals(myTicket.getPageTitleText(),"Manage ticket","Message is not displayed as expected.");
        ChangePassword changePassword = loginPage.goToChangePassWord();
        Assert.assertEquals(changePassword.getPageTitleText(),"Change password","Message is not displayed as expected.");
    }
}
