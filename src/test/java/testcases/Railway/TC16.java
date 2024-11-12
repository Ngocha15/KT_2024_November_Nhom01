package testcases.Railway;

import common.Constant.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.Railway.BookTicketPage;
import pageObjects.Railway.HomePage;
import pageObjects.Railway.LoginPage;
import pageObjects.Railway.MyTicket;

import java.util.List;

public class TC16 {
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
    public  void TC16() {
        String selectDate = "11/27/2024";
        String selectDepartStation = "Sài Gòn";
        String selectArriveStation = "Nha Trang";
        String selectSeatType = "Soft bed with air conditioner";
        String selectTicketAmount = "1";
        HomePage homePage = new HomePage().open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME,Constant.PASSWORD);
        BookTicketPage bookTicketPage = loginPage.goToBookTicket();
        bookTicketPage.bookTicketPage(selectDate,selectDepartStation,selectArriveStation,selectSeatType,selectTicketAmount);
        MyTicket myTicketPage = bookTicketPage.goToMyTicket();
        List<String> ids=  myTicketPage.getIds();
        String idUserSelected = myTicketPage.getAnRandomId();
        assert ids.contains(idUserSelected);
        MyTicket newTicketPage =  myTicketPage.cancelTicketWithId(idUserSelected);
        List<String> newIds = newTicketPage.getIds();
        Assert.assertFalse(newIds.contains(idUserSelected), "Ticket haven't been canceled");
    }
}
