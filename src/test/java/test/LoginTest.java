package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginSubmitPage;

public class LoginTest extends BaseTest {
    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                {"xodylj@ukr.net", "Kosmetista1990"},
                {"Xodylj@Ukr.Net", "Kosmetista1990"},
                {" XODylj@ukr.net", "Kosmetista1990"}
        };
    }

    @Test(dataProvider = "validDataProvider")
    public void successfulLoginTest(String userEmail, String userPassword) {
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");
        HomePage homePage = loginPage.login(userEmail, userPassword);
        Assert.assertTrue(homePage.isPageLoaded(), "Home page is not loaded.");
        homePage.clickOnProfileMenuItem();
        Assert.assertEquals(homePage.getProfileUserNameText(), "Member1 Number",
                "Wrong profile user name displayed.");
    }

    @Test
    public void negativeLoginWithEmptyFields() {
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");
        loginPage.login("", "");
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");
    }

    @DataProvider
    public Object[][] invalidDataProvider() {
        return new Object[][]{
//                { "xodylj@@ukr.net", "Kosmetista1990", "Hmm, we don't recognize that email. Please try again.", ""},
                {"xodylj@ukr.net", "123456", "", ""}
        };
    }

    @Test(dataProvider = "invalidDataProvider")
    public void negativeLoginWithInvalidData(String userEmail,
                                             String userPassword,
                                             String userEmailValidationMessage,
                                             String userPasswordValidationMessage) {
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");
        LoginSubmitPage loginSubmitPage = loginPage.login(userEmail, userPassword);
        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "LoginSubmit page is not loaded.");
        Assert.assertEquals(loginSubmitPage.getUserEmailValidationMessage(), userEmailValidationMessage,
                "Wrong validation message on user email.");
        Assert.assertEquals(loginSubmitPage.getUserPasswordValidationMessage(), userPasswordValidationMessage,
                "Wrong validation message on user password.");
    }
}