package testcases.Railway;

import common.Constant.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.Railway.HomePage;
import pageObjects.Railway.LoginPage;

public class TC02 {
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
}
