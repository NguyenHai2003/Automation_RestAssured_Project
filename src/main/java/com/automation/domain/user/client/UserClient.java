package com.automation.domain.user.client;

import com.automation.constants.EndPointGlobal;
import com.automation.keywords.ApiKeyword;
import io.restassured.response.Response;

public class UserClient {

    public Response getUsers() {
        return ApiKeyword.get(EndPointGlobal.USER);
    }
}
