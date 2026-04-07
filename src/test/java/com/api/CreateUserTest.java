package com.api;

import com.constants.CommonConstants;
import com.utils.LogUtil;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateUserTest {

    @Test(description = "POST /users - Create user and validate status 201")
    public void testCreateUser() {
        String requestBody = String.format("""
                {
                    "name": "%s",
                    "job": "%s"
                }
                """, CommonConstants.CREATE_USER_NAME, CommonConstants.CREATE_USER_JOB);

        LogUtil.info("Sending POST request to " + CommonConstants.USERS_ENDPOINT
                + " with name: " + CommonConstants.CREATE_USER_NAME
                + ", job: " + CommonConstants.CREATE_USER_JOB);

        Response response = given()
                .baseUri(CommonConstants.API_BASE_URI)
                .header("Content-Type", CommonConstants.CONTENT_TYPE_JSON)
                .header(CommonConstants.API_KEY_HEADER, CommonConstants.API_KEY)
                .body(requestBody)
            .when()
                .post(CommonConstants.USERS_ENDPOINT)
            .then()
                .statusCode(CommonConstants.STATUS_CREATED)
                .body("name", equalTo(CommonConstants.CREATE_USER_NAME))
                .body("job", equalTo(CommonConstants.CREATE_USER_JOB))
                .body("id", notNullValue())
                .body("createdAt", notNullValue())
                .extract().response();

        String name = response.jsonPath().getString("name");
        Assert.assertEquals(name, CommonConstants.CREATE_USER_NAME,
                "Created user name should be '" + CommonConstants.CREATE_USER_NAME + "'");

        String id = response.jsonPath().getString("id");
        Assert.assertNotNull(id, "Created user should have an ID");

        String createdAt = response.jsonPath().getString("createdAt");
        Assert.assertNotNull(createdAt, "Created user should have a createdAt timestamp");

        LogUtil.info("Create user test passed - ID: " + id + ", Name: " + name);
    }
}
