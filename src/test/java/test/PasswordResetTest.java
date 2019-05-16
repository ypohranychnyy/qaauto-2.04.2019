package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.*;

public class PasswordResetTest {

    @Test
    public void resetPasswordTest() {
        String userEmail = "xodylj@ukr.net",
                userPassword = "Kosmetista1990";

        ResetPasswordPage resetPasswordPage = loginPage.resetPassword();
        Assert.assertTrue(resetPasswordPage.isPageLoaded(), "Reset Password page is not loaded.");

        ResetPasswordLinkSentPage resetPasswordLinkSentPage = resetPasswordPage.submitUsername(userEmail);
        Assert.assertTrue(resetPasswordLinkSentPage.isPageLoaded(), "Reset Password Link Sent page is not loaded.");

        GmailLoginPage gmailLoginPage = resetPasswordLinkSentPage.redirectToGmailPage();
        Assert.assertTrue(gmailLoginPage.isPageLoaded(), "Page is not loaded.");

        gmailLoginPage.submitEmail(userEmail);
        GmailPage gmailPage = gmailLoginPage.submitPassword(userPassword);
        Assert.assertTrue(gmailPage.isPageLoaded(), "Page is not loaded.");

        EnterNewPasswordPage enterNewPasswordPage = gmailPage.goToResetLink();
        Assert.assertTrue(enterNewPasswordPage.isPageLoaded(), "Page is not loaded.");

        ResetPasswordSuccessPage resetPasswordSuccessPage = enterNewPasswordPage.submitNewPassword(password);
        Assert.assertTrue(resetPasswordSuccessPage.isPageLoaded(), "Page is not loaded.");

        HomePage homePage = resetPasswordSuccessPage.goToHomepage();
        Assert.assertTrue(homePage.isPageLoaded(), "Page is not loaded.");


    }
}

}
