package com.automation.domain.auth.assertion;

import com.automation.models.auth.AuthResponse;
import io.restassured.response.Response;

public final class AuthAssertions {

    private AuthAssertions() {
    }

    public static AuthResponse assertLoginSuccess(Response response) {
        response.then().statusCode(200);
        AuthResponse authResponse = response.as(AuthResponse.class);
        if (authResponse.getToken() == null || authResponse.getToken().isBlank()) {
            throw new AssertionError("Token must not be blank after successful authentication");
        }
        return authResponse;
    }
}
