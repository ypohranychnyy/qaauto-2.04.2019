package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

public class PasswordResetTest extends BaseTest {

    @Test
    public void resetPasswordTest() {
        String userEmail = "lnkdn.tst@gmail.com",
                userPassword = "testLink!";

        RequestPasswordResetPage requestPasswordResetPage =
                loginPage.clickOnForgotPasswordLink();
        Assert.assertTrue(requestPasswordResetPage.isLoaded(),
                "RequestPasswordReset page is not loaded.");

        RequestPasswordResetSubmitPage requestPasswordResetSubmitPage =
                requestPasswordResetPage.findAccount(userEmail);

        ChooseNewPasswordPage chooseNewPasswordPage =
                requestPasswordResetSubmitPage.navigateToLinkFromEmail();
    }
}
