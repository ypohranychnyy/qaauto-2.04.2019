import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    private WebDriver driver; //ToDo: should be private
    protected LoginPage loginPage;

    @BeforeMethod
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\yuriy.po\\Documents\\chromedriver.exe"); //working station
//        System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_win32\\chromedriver.exe"); //working station
//         System.setProperty("webdriver.chrome.driver", "/Users/yuriy/Documents/Webdriver/chromedriver"); //for MacOS
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.get("https://www.linkedin.com");
        loginPage = new LoginPage(driver);
        System.out.println("1st Before Method");
    }



    @AfterMethod
    public void afterMethod() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
