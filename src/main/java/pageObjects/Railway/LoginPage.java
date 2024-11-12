package pageObjects.Railway;

import common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends GeneralPage{

    //locator
    private final By _txtUsername = By.xpath("//input[@id='username']");
    private final By _txtPassword = By.xpath("//input[@id='password']");
    private final By _btnLogin = By.xpath("//input[@value='Login']");
    private final By _IbLLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");

    //elements
    public WebElement getTxtUsername(){
        return Constant.WEBDRIVER.findElement(_txtUsername);
    }
    public WebElement getTxtPassword(){
        return Constant.WEBDRIVER.findElement(_txtPassword);
    }
    public WebElement getBtnLogin(){
        return Constant.WEBDRIVER.findElement(_btnLogin);
    }
    public WebElement getLbILoginErrorMsg(){
        return Constant.WEBDRIVER.findElement(_IbLLoginErrorMsg);
    }



    public  void clearForm() {
         Constant.WEBDRIVER.findElement(_txtUsername).clear();

    }
    public HomePage login(String username, String password)
    {
        this.getTxtUsername().sendKeys(username);
        this.getTxtPassword().sendKeys(password);
        this.getBtnLogin().click();
        return new HomePage();
    }
}
