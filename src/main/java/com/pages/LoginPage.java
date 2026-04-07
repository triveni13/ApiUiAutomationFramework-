package com.pages;

import com.constants.CommonConstants;
import com.locators.Locators;
import com.utils.LogUtil;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        LogUtil.info("Opening SauceDemo login page");
        driver.get(CommonConstants.SAUCE_DEMO_URL);
        waitForVisibility(Locators.Login.LOGIN_BUTTON);
        LogUtil.info("Login page loaded successfully");
        return this;
    }

    public LoginPage enterUsername(String username) {
        LogUtil.info("Entering username: " + username);
        type(Locators.Login.USERNAME_FIELD, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        LogUtil.info("Entering password: ****");
        type(Locators.Login.PASSWORD_FIELD, password);
        return this;
    }

    public InventoryPage clickLogin() {
        LogUtil.info("Clicking Login button");
        click(Locators.Login.LOGIN_BUTTON);
        return new InventoryPage(driver);
    }

    public InventoryPage loginAs(String username, String password) {
        LogUtil.info("Logging in as: " + username);
        enterUsername(username);
        enterPassword(password);
        return clickLogin();
    }

    public String getErrorMessage() {
        String error = getText(Locators.Login.ERROR_MESSAGE);
        LogUtil.error("Login error message: " + error);
        return error;
    }
}
