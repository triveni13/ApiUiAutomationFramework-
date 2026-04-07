package com.constants;

public final class CommonConstants {

    private CommonConstants() {}

    // ======================== URLs ========================
    public static final String SAUCE_DEMO_URL = "https://www.saucedemo.com/";
    public static final String API_BASE_URI = "https://reqres.in/api";

    // ======================== Credentials ========================
    public static final String VALID_USERNAME = "standard_user";
    public static final String VALID_PASSWORD = "secret_sauce";

    // ======================== URL Fragments ========================
    public static final String INVENTORY_URL_FRAGMENT = "inventory.html";
    public static final String CART_URL_FRAGMENT = "cart.html";

    // ======================== Expected UI Texts ========================
    public static final String PRODUCTS_PAGE_TITLE = "Products";
    public static final String ORDER_SUCCESS_HEADER = "Thank you for your order!";
    public static final String CHECKOUT_COMPLETE_TITLE = "Checkout: Complete!";
    public static final String INVALID_CREDENTIALS_ERROR = "Username and password do not match";

    // ======================== Test Data ========================
    public static final String CHECKOUT_FIRST_NAME = "Triveni";
    public static final String CHECKOUT_LAST_NAME = "Automation";
    public static final String CHECKOUT_ZIP_CODE = "560001";

    // ======================== API Test Data ========================
    public static final String CREATE_USER_NAME = "Triveni";
    public static final String CREATE_USER_JOB = "QA Engineer";

    // ======================== API Expected Values ========================
    public static final int EXPECTED_USER_ID = 2;
    public static final String EXPECTED_USER_EMAIL = "janet.weaver@reqres.in";
    public static final String EXPECTED_USER_FIRST_NAME = "Janet";
    public static final String EXPECTED_USER_LAST_NAME = "Weaver";

    // ======================== API Endpoints ========================
    public static final String USERS_ENDPOINT = "/users";
    public static final String USER_BY_ID_ENDPOINT = "/users/2";

    // ======================== HTTP Status Codes ========================
    public static final int STATUS_OK = 200;
    public static final int STATUS_CREATED = 201;
    public static final int STATUS_NO_CONTENT = 204;

    // ======================== API Authentication ========================
    public static final String API_KEY_HEADER = "x-api-key";
    public static final String API_KEY = "pro_9faafed64ef60ce458ebfd79fb378b1204c4400510f46deac6276947e90ff7ce";

    // ======================== Misc ========================
    public static final String CONTENT_TYPE_JSON = "application/json";
}
