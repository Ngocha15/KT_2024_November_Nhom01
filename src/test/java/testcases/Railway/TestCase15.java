package testcases.Railway;

import common.Constant.Constant;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Railway.BookTicketPage;
import pageObjects.Railway.HomePage;
import pageObjects.Railway.LoginPage;
import pageObjects.Railway.TimeTablePage;

public class TestCase15 extends PreparationCommonTest{
    @Test
    public void TC15() {
        System.out.println("TC15 - User can open 'Book ticket' page by clicking on 'Book ticket' link in 'Train timetable' page");
        HomePage homePage = new HomePage().open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME,Constant.PASSWORD);

        TimeTablePage timeTablePage = loginPage.goToTimeTablePage();
        timeTablePage.clickBookTicketLink("Huế","Sài Gòn");

        BookTicketPage bookTicketPage = new BookTicketPage();
        Assert.assertEquals(bookTicketPage.isDepartValuesCorrect("Huế"),true,"Depart from is not correct");
        Assert.assertEquals(bookTicketPage.isArriveValuesCorrect("Sài Gòn"),true,"Arrive at is not correct");

    }
}
