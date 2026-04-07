package com.api;

import com.constants.CommonConstants;
import com.utils.LogUtil;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUserTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = CommonConstants.API_BASE_URI;
        LogUtil.info("API Base URI set to: " + CommonConstants.API_BASE_URI);
    }

    @Test(description = "DELETE /users/2 - Validate status code 204")
    public void testDeleteUser() {
        LogUtil.info("Sending DELETE request to " + CommonConstants.USER_BY_ID_ENDPOINT);

        given()
                .header("Content-Type", CommonConstants.CONTENT_TYPE_JSON)
                .header(CommonConstants.API_KEY_HEADER, CommonConstants.API_KEY)
            .when()
                .delete(CommonConstants.USER_BY_ID_ENDPOINT)
            .then()
                .statusCode(CommonConstants.STATUS_NO_CONTENT);

        LogUtil.info("Delete user test passed - Status 204 received");
    }
}
