import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {
    @Test
    public void SuccessfulLoginTest() {
        System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_win32\\chromedriver.exe"); //working station
//         System.setProperty("webdriver.chrome.driver", "/Users/yuriy/Documents/Webdriver/chromedriver"); //for MacOS
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("xodylj@ukr.net", "Kosmetista1990");
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isProfileMenuItemDisplayed());
        homePage.clickOnProfileMenuItem();
        Assert.assertEquals(homePage.getProfileUserNameText(), "Member1 Number",
                "Wromg profile username is displayed");
//        driver.quit();
    }

    @Test
    public void negativeLoginTest() {
        System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_win32\\chromedriver.exe"); //working station
//         System.setProperty("webdriver.chrome.driver", "/Users/yuriy/Documents/Webdriver/chromedriver"); //for MacOS
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("t@ukr.net", "Kosmetista1990");
        SubmitPage submitPage = new SubmitPage(driver);
        Assert.assertTrue(submitPage.isLoginErrorMessageDisplayed());
        Assert.assertEquals(submitPage.getLoginErrorMessageText(),
                "Hmm, that's not the right password. Please try again or request a new one.\\n");
//        driver.quit();
    }

    @Test
    public void negativeLoginTest2() {
        System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_win32\\chromedriver.exe"); //working station
//         System.setProperty("webdriver.chrome.driver", "/Users/yuriy/Documents/Webdriver/chromedriver"); //for MacOS
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("k@ukr.net", "12341");
        SubmitPage submitPage = new SubmitPage(driver);
        Assert.assertTrue(submitPage.isPasswordErrorMessageDisplayed());
        Assert.assertEquals(submitPage.getPasswordErrorMessageText(),
                "Hmm, we don't recognize that email. Please try again.");

        //        driver.quit();
    }
}
