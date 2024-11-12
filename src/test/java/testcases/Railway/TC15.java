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
import pageObjects.Railway.TimeTablePage;

public class TC15 {
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
}
