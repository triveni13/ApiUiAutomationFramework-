package com.ui;

import com.BaseTest;
import com.constants.CommonConstants;
import com.pages.InventoryPage;
import com.pages.LoginPage;
import com.utils.LogUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Verify successful login with valid credentials")
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();

        InventoryPage inventoryPage = loginPage.loginAs(
                CommonConstants.VALID_USERNAME, CommonConstants.VALID_PASSWORD);

        Assert.assertTrue(inventoryPage.isOnInventoryPage(),
                "User should land on the inventory page after login");
        Assert.assertEquals(inventoryPage.getPageTitle(), CommonConstants.PRODUCTS_PAGE_TITLE,
                "Page title should be 'Products'");
        LogUtil.info("Valid login test passed");
    }

    @Test(description = "Verify login fails with invalid credentials")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();

        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword("wrong_password");
        loginPage.clickLogin();

        String errorMsg = loginPage.getErrorMessage();
        Assert.assertTrue(errorMsg.contains(CommonConstants.INVALID_CREDENTIALS_ERROR),
                "Error message should indicate invalid credentials");
        LogUtil.info("Invalid login test passed - error displayed as expected");
    }
}
