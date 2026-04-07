package com.pages;

import com.constants.CommonConstants;
import com.locators.Locators;
import com.utils.LogUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends BasePage {

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnInventoryPage() {
        boolean result = getCurrentUrl().contains(CommonConstants.INVENTORY_URL_FRAGMENT)
                && isDisplayed(Locators.Inventory.PAGE_TITLE);
        LogUtil.info("On inventory page: " + result);
        return result;
    }

    public String getPageTitle() {
        return getText(Locators.Inventory.PAGE_TITLE);
    }

    public List<String> addMultipleProductsToCart(int count) {
        LogUtil.info("Adding " + count + " products to cart");
        List<WebElement> items = driver.findElements(Locators.Inventory.INVENTORY_ITEMS);
        List<String> addedProducts = new ArrayList<>();

        for (int i = 0; i < count && i < items.size(); i++) {
            WebElement item = items.get(i);
            String productName = item.findElement(Locators.Inventory.ITEM_NAME).getText();
            item.findElement(Locators.Inventory.ADD_TO_CART_BUTTON).click();
            addedProducts.add(productName);
            LogUtil.info("Added product to cart: " + productName);
        }

        return addedProducts;
    }

    public int getCartBadgeCount() {
        try {
            int count = Integer.parseInt(getText(Locators.Inventory.CART_BADGE));
            LogUtil.info("Cart badge count: " + count);
            return count;
        } catch (Exception e) {
            LogUtil.warn("Cart badge not visible, returning 0");
            return 0;
        }
    }

    public CartPage openCart() {
        LogUtil.info("Opening cart page");
        click(Locators.Inventory.CART_LINK);
        return new CartPage(driver);
    }

    public String getProductNameByIndex(int index) {
        List<WebElement> items = driver.findElements(Locators.Inventory.INVENTORY_ITEMS);
        String name = items.get(index).findElement(Locators.Inventory.ITEM_NAME).getText();
        LogUtil.info("Product at index " + index + ": " + name);
        return name;
    }
}
