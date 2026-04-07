package com.locators;

import org.openqa.selenium.By;

public final class Locators {

    private Locators() {}

    public static final class Login {
        public static final By USERNAME_FIELD = By.id("user-name");
        public static final By PASSWORD_FIELD = By.id("password");
        public static final By LOGIN_BUTTON = By.id("login-button");
        public static final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");
    }

    public static final class Inventory {
        public static final By INVENTORY_ITEMS = By.className("inventory_item");
        public static final By PAGE_TITLE = By.className("title");
        public static final By CART_BADGE = By.className("shopping_cart_badge");
        public static final By CART_LINK = By.className("shopping_cart_link");
        public static final By ADD_TO_CART_BUTTON = By.cssSelector("button[id^='add-to-cart']");
        public static final By ITEM_NAME = By.className("inventory_item_name");
    }

    public static final class Cart {
        public static final By CART_ITEMS = By.className("cart_item");
        public static final By CART_ITEM_NAMES = By.className("inventory_item_name");
        public static final By CHECKOUT_BUTTON = By.id("checkout");
        public static final By PAGE_TITLE = By.className("title");
    }

    public static final class Checkout {
        public static final By FIRST_NAME_FIELD = By.id("first-name");
        public static final By LAST_NAME_FIELD = By.id("last-name");
        public static final By ZIP_CODE_FIELD = By.id("postal-code");
        public static final By CONTINUE_BUTTON = By.id("continue");
        public static final By FINISH_BUTTON = By.id("finish");
        public static final By COMPLETE_HEADER = By.className("complete-header");
        public static final By PAGE_TITLE = By.className("title");
    }
}
