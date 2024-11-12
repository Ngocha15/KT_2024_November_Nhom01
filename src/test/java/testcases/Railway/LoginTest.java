package testcases.Railway;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import common.Constant.Constant;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.Railway.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginTest {
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
    public void TC01() {
        System.out.println("TC01 - User can log into Railway with valid username and password");

        HomePage homePage= new HomePage().open();
        LoginPage loginPage = homePage.gotoLoginPage();

        String actualMsg = loginPage.login(Constant.USERNAME, Constant.PASSWORD).getWelcomeMessage();
        String expectedMsg = "Welcome " + Constant.USERNAME;
        Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
    }
    @Test
    public void TC02() {

            System.out.println("TC02 - User can't login with blank 'Username' textbox");
            // Khởi tạo trang chủ và mở trang
            HomePage homePage= new HomePage().open();
            LoginPage loginPage = homePage.gotoLoginPage();

            // Để trống Username, chỉ nhập mật khẩu hợp lệ vào Password và nhấn Login
            String actualMsg = loginPage.login("",Constant.PASSWORD).getErrorMassage();
            String expectedMsg = "There was a problem with your login and/or errors exist in your form.";
            Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected.");
    }
    @Test
    public void TC03() {
        System.out.println("TC03 - User can't login with invalid 'password'");
        HomePage homePage = new HomePage().open();

        LoginPage loginPage = homePage.gotoLoginPage();
        String actualMsg = loginPage.login(Constant.USERNAME,"").getErrorMassage().trim();
        String expectedMsg =  "There was a problem with your login and/or errors exist in your form.";
        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected.");
    }
    @Test
    public void TC04() {
        System.out.println("Login page displays when un-logged User clicks on 'Book ticket' tab");
        HomePage homePage = new HomePage().open();

        String actualMsg = homePage.goToBookTicket().isLoginPage();
        String expectedMsg = "Login Page";
        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected.");
    }
    @Test
    public void TC05() {
        System.out.println("System shows message when user enters wrong password several times");
        HomePage homePage = new HomePage().open();

        LoginPage loginPage = homePage.gotoLoginPage();

        for (int i = 1; i <=3; i++) {
            loginPage.login(Constant.USERNAME,"hahahaha");
            loginPage.clearForm();
        }
        String actualMsg = loginPage.login(Constant.USERNAME,"hahaha").getErrorPassMsg();
        String expectedMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
        Assert.assertEquals(actualMsg,expectedMsg,"Error message is not displayed as expected.");

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
    @Test
    public void TC07() {
        System.out.println("User can create new account");

        HomePage homePage = new HomePage().open();

        RegisterPage registerPage = homePage.gotoRegisterPage();

       String actualMsg =  registerPage.register("gaugauu@gmail.com","123456789","123456789","123456789").getWelcomeRegisterMsg();
       String expectedMsg =  "Thank you for registering your account.";
        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected.");
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
    @Test
    public void TC09() {
        System.out.println("User can change password");
        HomePage homePage = new HomePage().open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME,Constant.PASSWORD);

        ChangePassword changePassword  = loginPage.goToChangePassWord();
        String actualMsg = changePassword.changePassword("0987654321","0987654321","0987654321").getChangePasswordMsg();
        String expectedMsg =  "Your password has been updated";
        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected.");
    }
    @Test
    public void TC10() {
        System.out.println("User can't create account with 'Confirm password' is not the same with 'Password'");
        HomePage homePage = new HomePage().open();
        RegisterPage registerPage = homePage.gotoRegisterPage();

        String actualMsg = registerPage.register("meomeo@gmail.com","12345678","87654321","12345678").getErrorConfirmPasswordMsg();
        String expectedMsg =  "There're errors in the form. Please correct the errors and try again.";
        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected.");


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
    @Test
    public void TC12() {
        System.out.println("Errors display when password reset token is blank");
        HomePage homePage = new HomePage().open();
        LoginPage loginPage = homePage.gotoLoginPage();
        ForgotPassword forgotPassword = loginPage.goToForgotPasswordPage();
        forgotPassword.forgotPassword("haha@gmail.com");
    }
    @Test
    public void TC13() {
        System.out.println("Errors display if password and confirm password don't match when resetting password");
        HomePage homePage = new HomePage().open();
        LoginPage loginPage = homePage.gotoLoginPage();
        ForgotPassword forgotPassword = loginPage.goToForgotPasswordPage();
        forgotPassword.forgotPassword("haha@gmail.com");
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
    @Test
    public void TC15() {
        HomePage homePage = new HomePage().open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME,Constant.PASSWORD);

        TimeTablePage timeTablePage = loginPage.goToTimeTablePage();
        timeTablePage.clickBookTicketLink("Huế","Sài Gòn");

        BookTicketPage bookTicketPage = new BookTicketPage();
        Assert.assertEquals(bookTicketPage.isDepartValuesCorrect(),"Huế","Depart from is not correct");
        Assert.assertEquals(bookTicketPage.isArriveValuesCorrect(),"Sài Gòn","Arrive at is not correct");

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
