import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod

    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\yuriy.po\\Documents\\chromedriver.exe"); //working station
//        System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_win32\\chromedriver.exe"); //working station
//         System.setProperty("webdriver.chrome.driver", "/Users/yuriy/Documents/Webdriver/chromedriver"); //for MacOS
        driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
//                {"xodylj@ukr.net", "Kosmetista1990"},
                {"Xodylj@ukr.net", "Kosmetista1990"}
        };
    }

    @Test(dataProvider = "validDataProvider")
    public void SuccessfulLoginTest(String userEmail, String userPassword) {
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up ");
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page is not loaded");

        HomePage homePage = loginPage.login(userEmail, userPassword);

        Assert.assertTrue(homePage.isProfileMenuItemDisplayed());
        homePage.clickOnProfileMenuItem();
        Assert.assertEquals(homePage.getProfileUserNameText(), "Member1 Number",
                "Wromg profile username is displayed");
    }

    @DataProvider
    public Object[][] invalidLoginDataProvider() {
        return new Object[][]{
                {"xodylj", "Kosmetista1990"},
                {"@Ukr.net", "Kosmetista1990"}
        };
    }

    @Test(dataProvider = "invalidLoginDataProvider")
    public void invalidLoginTest(String userEmail, String userPassword) {
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page is not loaded");
        SubmitPage submitPage = loginPage.loginToSubmitPage(userEmail, userPassword);
        Assert.assertTrue(submitPage.isLoginErrorMessageDisplayed());
        Assert.assertEquals(submitPage.getLoginErrorMessageText(),
                "Hmm, we don't recognize that email. Please try again.");
    }

    @DataProvider
    public Object[][] invalidPasswordDataProvider() {
        return new Object[][]{
                {"xodylj@ukr.net", "kosmetista1990"},
                {"xodylj@ukr.net", " d"}
        };
    }

    @Test(dataProvider = "invalidPasswordDataProvider")
    public void invalidPasswordLoginTest(String userEmail, String userPassword) {
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page is not loaded");
        loginPage.login(userEmail, userPassword);
        SubmitPage submitPage = loginPage.loginToSubmitPage(userEmail, userPassword);
        Assert.assertTrue(submitPage.isPasswordErrorMessageDisplayed());
        Assert.assertEquals(submitPage.getPasswordErrorMessageText(),
                "Hmm, that's not the right password. Please try again or request a new one.");
    }

    @Test
    public void emptyFieldsLoginTest() {
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up ");
        loginPage.login("", "");
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page is not loaded");
    }

    @DataProvider
    public Object[][] invalidDataProvider() {
        return new Object[][]{
                {"xodylj@@ukr.net", "Kosmetista1990", "Hmm, we don't recognize that email. Please try again.", ""},
                {"Xodylj@ukr.net", "123456", "", "Hmm, that's not the right password. Please try again or request a new one."}
        };
    }

    @Test(dataProvider = "invalidDataProvider")
    public void negativeLoginWithInvalidPasswordData(String userEmail,
                                                     String userPassword,
                                                     String userEmailValidationMessage,
                                                     String userPasswordValidationMessage
    ) {
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up ");
        loginPage.login(userEmail, userPassword);
        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(driver);
        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "Login Submit Page is not loaded");
        Assert.assertEquals(loginSubmitPage.getUserEmailValidationMessage(), userEmailValidationMessage, "Wrong validation message on user email");
        Assert.assertEquals(loginSubmitPage.getUserPasswordValidationMessage(), userPasswordValidationMessage, "Wrong validation message on user password");
    }
}
