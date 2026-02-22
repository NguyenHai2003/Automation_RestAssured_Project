package com.automation.keywords;

import com.automation.constants.EndPointGlobal;
import com.automation.globals.ConfigsGlobal;
import com.automation.globals.TokenGlobal;
import com.automation.models.auth.AuthRequest;
import com.automation.models.auth.AuthResponse;
import com.automation.utils.LogUtils;
import io.restassured.response.Response;

public class Authentication {

    public static void login() {
        AuthRequest authRequest = AuthRequest.builder()
                .username(ConfigsGlobal.USERNAME)
                .password(ConfigsGlobal.PASSWORD)
                .build();

        Response response = ApiKeyword.post(EndPointGlobal.AUTH, authRequest);
        response.then().statusCode(200);

        AuthResponse authResponse = response.as(AuthResponse.class);
        TokenGlobal.TOKEN = authResponse.getToken();
        LogUtils.info("Token retrieved: " + TokenGlobal.TOKEN);
    }
}
