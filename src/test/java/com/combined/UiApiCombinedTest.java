package com.combined;

import com.BaseTest;
import com.constants.CommonConstants;
import com.pages.InventoryPage;
import com.pages.LoginPage;
import com.utils.LogUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UiApiCombinedTest extends BaseTest {

    @Test(description = "Login via UI, fetch user via API, and cross-validate data")
    public void testUiLoginAndApiFetch() {
        LogUtil.info("--- UI Phase: Login ---");
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        InventoryPage inventoryPage = loginPage.loginAs(
                CommonConstants.VALID_USERNAME, CommonConstants.VALID_PASSWORD);

        Assert.assertTrue(inventoryPage.isOnInventoryPage(),
                "User should land on inventory page after login");

        String firstProductFromUI = inventoryPage.getProductNameByIndex(0);
        Assert.assertNotNull(firstProductFromUI,
                "Should be able to read product name from UI");

        LogUtil.info("--- API Phase: Fetch user ---");
        RestAssured.baseURI = CommonConstants.API_BASE_URI;

        Response response = given()
                .header("Content-Type", CommonConstants.CONTENT_TYPE_JSON)
                .header(CommonConstants.API_KEY_HEADER, CommonConstants.API_KEY)
            .when()
                .get(CommonConstants.USER_BY_ID_ENDPOINT)
            .then()
                .statusCode(CommonConstants.STATUS_OK)
                .extract().response();

        int userId = response.jsonPath().getInt("data.id");
        String firstName = response.jsonPath().getString("data.first_name");
        String email = response.jsonPath().getString("data.email");

        Assert.assertEquals(userId, CommonConstants.EXPECTED_USER_ID,
                "API should return user with id " + CommonConstants.EXPECTED_USER_ID);
        Assert.assertNotNull(firstName, "API user first_name should not be null");
        Assert.assertNotNull(email, "API user email should not be null");

        LogUtil.info("--- Cross-Validation Phase ---");
        Assert.assertTrue(inventoryPage.isOnInventoryPage(),
                "UI session should still be active after API call");
        Assert.assertFalse(firstProductFromUI.isEmpty(),
                "UI product name should not be empty");
        Assert.assertFalse(firstName.isEmpty(),
                "API first_name should not be empty");

        LogUtil.info("Combined test passed - UI Product: " + firstProductFromUI
                + ", API User: " + firstName + " (" + email + ")");
    }
}
