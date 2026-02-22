package com.automation.domain.auth.client;

import com.automation.constants.EndPointGlobal;
import com.automation.models.auth.AuthRequest;
import com.automation.keywords.ApiKeyword;
import io.restassured.response.Response;

public class AuthClient {

    public Response login(AuthRequest authRequest) {
        return ApiKeyword.post(EndPointGlobal.AUTH, authRequest);
    }
}
