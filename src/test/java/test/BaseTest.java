package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import page.LoginPage;

public class BaseTest {
    protected LoginPage loginPage;
    protected WebDriver driver;

    @BeforeMethod

    @Parameters("browser")

    public void beforeMethod(@Optional("chrome") String browserName) throws Exception {
        if (browserName.toLowerCase().equals("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if (browserName.toLowerCase().equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else {
            throw new Exception("Unsupported 'BrowserName'");
        }

       // System.setProperty("webdriver.chrome.driver", "C:\\Users\\yuriy.po\\Documents\\chromedriver.exe"); //working station
//        System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_win32\\chromedriver.exe"); //working station
//         System.setProperty("webdriver.chrome.driver", "/Users/yuriy/Documents/Webdriver/chromedriver"); //for MacOS
        driver.manage().window().maximize();
//        driver.manage().deleteAllCookies();
        driver.get("http://linkedin.com");
        loginPage = new LoginPage(driver);
//        System.out.println("1st Before Method"); //BeforeAll method
    }

    @AfterMethod
    public void afterMethod() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
