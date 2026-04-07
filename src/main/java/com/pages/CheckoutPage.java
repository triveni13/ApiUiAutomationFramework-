package com.pages;

import com.locators.Locators;
import com.utils.LogUtil;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage enterFirstName(String firstName) {
        LogUtil.info("Entering first name: " + firstName);
        type(Locators.Checkout.FIRST_NAME_FIELD, firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        LogUtil.info("Entering last name: " + lastName);
        type(Locators.Checkout.LAST_NAME_FIELD, lastName);
        return this;
    }

    public CheckoutPage enterZipCode(String zipCode) {
        LogUtil.info("Entering zip code: " + zipCode);
        type(Locators.Checkout.ZIP_CODE_FIELD, zipCode);
        return this;
    }

    public CheckoutPage fillShippingInfo(String firstName, String lastName, String zipCode) {
        LogUtil.info("Filling shipping info");
        enterFirstName(firstName);
        enterLastName(lastName);
        enterZipCode(zipCode);
        return this;
    }

    public CheckoutPage clickContinue() {
        LogUtil.info("Clicking Continue button");
        click(Locators.Checkout.CONTINUE_BUTTON);
        return this;
    }

    public CheckoutPage clickFinish() {
        LogUtil.info("Clicking Finish button");
        click(Locators.Checkout.FINISH_BUTTON);
        return this;
    }

    public String getCompleteHeader() {
        return getText(Locators.Checkout.COMPLETE_HEADER);
    }

    public boolean isOrderComplete() {
        boolean result = isDisplayed(Locators.Checkout.COMPLETE_HEADER);
        LogUtil.info("Order complete: " + result);
        return result;
    }

    public String getPageTitle() {
        return getText(Locators.Checkout.PAGE_TITLE);
    }

    public CheckoutPage completeOrder(String firstName, String lastName, String zipCode) {
        LogUtil.info("Completing order");
        fillShippingInfo(firstName, lastName, zipCode);
        clickContinue();
        clickFinish();
        return this;
    }
}
