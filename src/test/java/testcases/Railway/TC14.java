package testcases.Railway;

import common.Constant.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.Railway.BookTicketPage;
import pageObjects.Railway.HomePage;
import pageObjects.Railway.LoginPage;

import java.util.Map;

public class TC14 {
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
    public  void TC14() {
        SoftAssert softAssert = new SoftAssert();

        String selectDate = "11/27/2024";
        String selectDepartStation = "Sài Gòn";
        String selectArriveStation = "Nha Trang";
        String selectSeatType = "Soft bed with air conditioner";
        String selectTicketAmount = "1";

        System.out.println("User can book 1 ticket at a time");
        HomePage homePage = new HomePage().open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME,Constant.PASSWORD);
        BookTicketPage bookTicketPage = loginPage.goToBookTicket();

        String actualMsg = bookTicketPage.bookTicketPage(selectDate,selectDepartStation,selectArriveStation,selectSeatType,selectTicketAmount).getSuccessMesage();
        String expectedMsg = "Ticket booked successfully!";

        Map<String,String> ticketInformation = bookTicketPage.getTicketInformation();
        softAssert.assertEquals(actualMsg,expectedMsg,"TEST FAIL: Error message is not displayed as expected.");
        softAssert.assertEquals(ticketInformation.get("Depart Date"),selectDate,"TEST FAIL: Ticket information is not displayed correctly");
        softAssert.assertEquals(ticketInformation.get("Depart Station"),selectDepartStation,"TEST FAIL: Ticket information is not displayed correctly");
        softAssert.assertEquals(ticketInformation.get("Arrive Station"),selectArriveStation,"TEST FAIL: Ticket information is not displayed correctly");
        softAssert.assertEquals(ticketInformation.get("Seat Type"),selectSeatType,"TEST FAIL: Ticket information is not displayed correctly");
        softAssert.assertEquals(ticketInformation.get("Amount"),selectTicketAmount,"TEST FAIL: Ticket information is not displayed correctly");
        softAssert.assertAll();
    }
}
