package com.pages;

import com.constants.CommonConstants;
import com.locators.Locators;
import com.utils.LogUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnCartPage() {
        boolean result = getCurrentUrl().contains(CommonConstants.CART_URL_FRAGMENT)
                && isDisplayed(Locators.Cart.PAGE_TITLE);
        LogUtil.info("On cart page: " + result);
        return result;
    }

    public int getCartItemCount() {
        int count = driver.findElements(Locators.Cart.CART_ITEMS).size();
        LogUtil.info("Cart item count: " + count);
        return count;
    }

    public List<String> getCartItemNames() {
        List<WebElement> items = driver.findElements(Locators.Cart.CART_ITEM_NAMES);
        List<String> names = items.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        LogUtil.info("Cart items: " + names);
        return names;
    }

    public boolean containsProduct(String productName) {
        boolean found = getCartItemNames().contains(productName);
        LogUtil.info("Cart contains '" + productName + "': " + found);
        return found;
    }

    public CheckoutPage clickCheckout() {
        LogUtil.info("Clicking Checkout button");
        click(Locators.Cart.CHECKOUT_BUTTON);
        return new CheckoutPage(driver);
    }
}
