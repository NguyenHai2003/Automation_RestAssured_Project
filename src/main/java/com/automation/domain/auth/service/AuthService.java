package com.automation.domain.auth.service;

import com.automation.domain.auth.assertion.AuthAssertions;
import com.automation.domain.auth.client.AuthClient;
import com.automation.globals.ConfigsGlobal;
import com.automation.globals.TokenContext;
import com.automation.models.auth.AuthRequest;
import com.automation.models.auth.AuthResponse;
import io.restassured.response.Response;

public class AuthService {

    private final AuthClient authClient = new AuthClient();

    public String loginWithDefaultCredentials() {
        AuthRequest authRequest = AuthRequest.builder()
                .username(ConfigsGlobal.USERNAME)
                .password(ConfigsGlobal.PASSWORD)
                .build();

        Response response = authClient.login(authRequest);
        AuthResponse authResponse = AuthAssertions.assertLoginSuccess(response);
        TokenContext.setToken(authResponse.getToken());
        return authResponse.getToken();
    }
}
