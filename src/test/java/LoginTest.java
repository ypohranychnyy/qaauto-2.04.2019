import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                { "xodylj@ukr.net", "Kosmetista1990" },
                { "Xodylj@ukr.net", "Kosmetista1990" }
        };
    }

    @Test(dataProvider = "validDataProvider")
    public void SuccessfulLoginTest(String userEmail, String userPasword) {
        System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_win32\\chromedriver.exe"); //working station
//         System.setProperty("webdriver.chrome.driver", "/Users/yuriy/Documents/Webdriver/chromedriver"); //for MacOS
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");

        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up ");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userEmail, userPasword);
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isProfileMenuItemDisplayed());
        homePage.clickOnProfileMenuItem();
        Assert.assertEquals(homePage.getProfileUserNameText(), "Member1 Number",
                "Wromg profile username is displayed");
//        driver.quit();
    }

    @Test
    public void invalidLoginTest() {
        System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_win32\\chromedriver.exe"); //working station
//         System.setProperty("webdriver.chrome.driver", "/Users/yuriy/Documents/Webdriver/chromedriver"); //for MacOS
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("t@ukr.net", "Kosmetista1990");
        SubmitPage submitPage = new SubmitPage(driver);
        Assert.assertTrue(submitPage.isLoginErrorMessageDisplayed());
        Assert.assertEquals(submitPage.getLoginErrorMessageText(),
                "Hmm, we don't recognize that email. Please try again.");
//        driver.quit();
    }

    @Test
    public void invalidPasswordLoginTest() {
        System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_win32\\chromedriver.exe"); //working station
//         System.setProperty("webdriver.chrome.driver", "/Users/yuriy/Documents/Webdriver/chromedriver"); //for MacOS
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("xodylj@ukr.net", "12341");
        SubmitPage submitPage = new SubmitPage(driver);
        Assert.assertTrue(submitPage.isPasswordErrorMessageDisplayed());
        Assert.assertEquals(submitPage.getPasswordErrorMessageText(),
                "Hmm, that's not the right password. Please try again or request a new one.");

        //        driver.quit();
    }

    @Test
    public void emptyFieldsLoginTest() {
        System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_win32\\chromedriver.exe"); //working station
//         System.setProperty("webdriver.chrome.driver", "/Users/yuriy/Documents/Webdriver/chromedriver"); //for MacOS
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up ");

        loginPage.login("", "");
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page is not loaded");
    }
}
