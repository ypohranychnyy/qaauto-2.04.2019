package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.LoginPage;

public class BaseTest {
    protected LoginPage loginPage;
    protected WebDriver driver;
//    private WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\yuriy.po\\Documents\\chromedriver.exe"); //working station
//        System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_win32\\chromedriver.exe"); //working station
         System.setProperty("webdriver.chrome.driver", "/Users/yuriy/Documents/Webdriver/chromedriver"); //for MacOS
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.get("https://www.linkedin.com");
        loginPage = new LoginPage(driver);
//        System.out.println("1st Before Method"); //BeforeAll method
    }


    @AfterMethod
    public void afterMethod() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
