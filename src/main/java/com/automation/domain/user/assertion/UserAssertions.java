package com.automation.domain.user.assertion;

import io.restassured.response.Response;

public final class UserAssertions {

    private UserAssertions() {
    }

    public static void assertSuccess(Response response) {
        response.then().statusCode(200);
    }
}
