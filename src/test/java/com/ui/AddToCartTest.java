package com.ui;

import com.BaseTest;
import com.constants.CommonConstants;
import com.pages.CartPage;
import com.pages.InventoryPage;
import com.pages.LoginPage;
import com.utils.LogUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AddToCartTest extends BaseTest {

    @Test(description = "Verify adding 2 products to cart and verifying them in cart")
    public void testAddTwoProductsToCart() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        InventoryPage inventoryPage = loginPage.loginAs(
                CommonConstants.VALID_USERNAME, CommonConstants.VALID_PASSWORD);

        List<String> addedProducts = inventoryPage.addMultipleProductsToCart(2);

        Assert.assertEquals(inventoryPage.getCartBadgeCount(), 2,
                "Cart badge should show 2 items");

        CartPage cartPage = inventoryPage.openCart();

        Assert.assertTrue(cartPage.isOnCartPage(),
                "User should be on the cart page");
        Assert.assertEquals(cartPage.getCartItemCount(), 2,
                "Cart should contain exactly 2 items");

        for (String productName : addedProducts) {
            Assert.assertTrue(cartPage.containsProduct(productName),
                    "Cart should contain product: " + productName);
        }
        LogUtil.info("Add to cart test passed - 2 products verified in cart");
    }
}
