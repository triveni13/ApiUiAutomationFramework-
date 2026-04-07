package com.api;

import com.constants.CommonConstants;
import com.utils.LogUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetUserTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = CommonConstants.API_BASE_URI;
        LogUtil.info("API Base URI set to: " + CommonConstants.API_BASE_URI);
    }

    @Test(description = "GET /users/2 - Validate status 200 and response contains id: 2")
    public void testGetUserById() {
        LogUtil.info("Sending GET request to " + CommonConstants.USER_BY_ID_ENDPOINT);

        Response response = given()
                .header("Content-Type", CommonConstants.CONTENT_TYPE_JSON)
                .header(CommonConstants.API_KEY_HEADER, CommonConstants.API_KEY)
            .when()
                .get(CommonConstants.USER_BY_ID_ENDPOINT)
            .then()
                .statusCode(CommonConstants.STATUS_OK)
                .body("data.id", equalTo(CommonConstants.EXPECTED_USER_ID))
                .body("data.email", equalTo(CommonConstants.EXPECTED_USER_EMAIL))
                .body("data.first_name", equalTo(CommonConstants.EXPECTED_USER_FIRST_NAME))
                .body("data.last_name", equalTo(CommonConstants.EXPECTED_USER_LAST_NAME))
                .extract().response();

        int userId = response.jsonPath().getInt("data.id");
        Assert.assertEquals(userId, CommonConstants.EXPECTED_USER_ID, "User ID should be 2");

        String email = response.jsonPath().getString("data.email");
        Assert.assertNotNull(email, "Email should not be null");

        LogUtil.info("GET user test passed - User ID: " + userId + ", Email: " + email);
    }
}
