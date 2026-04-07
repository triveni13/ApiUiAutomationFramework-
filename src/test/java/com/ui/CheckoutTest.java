package com.ui;

import com.BaseTest;
import com.constants.CommonConstants;
import com.pages.CheckoutPage;
import com.pages.InventoryPage;
import com.pages.LoginPage;
import com.utils.LogUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test(description = "Verify complete checkout flow with order confirmation")
    public void testCheckoutFlow() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();

        InventoryPage inventoryPage = loginPage.loginAs(
                CommonConstants.VALID_USERNAME, CommonConstants.VALID_PASSWORD);
        inventoryPage.addMultipleProductsToCart(2);

        CheckoutPage checkoutPage = inventoryPage.openCart().clickCheckout();

        checkoutPage.completeOrder(
                CommonConstants.CHECKOUT_FIRST_NAME,
                CommonConstants.CHECKOUT_LAST_NAME,
                CommonConstants.CHECKOUT_ZIP_CODE);

        Assert.assertTrue(checkoutPage.isOrderComplete(),
                "Order should be completed successfully");
        Assert.assertEquals(checkoutPage.getCompleteHeader(), CommonConstants.ORDER_SUCCESS_HEADER,
                "Success header message should match");
        Assert.assertEquals(checkoutPage.getPageTitle(), CommonConstants.CHECKOUT_COMPLETE_TITLE,
                "Page title should be 'Checkout: Complete!'");
        LogUtil.info("Checkout test passed - order completed successfully");
    }
}
