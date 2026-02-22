package com.automation.keywords;

import com.automation.globals.TokenGlobal;
import com.automation.utils.LogUtils;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class ApiKeyword {

    private static RequestSpecification request;

    @Step("Get Request Specification")
    public static RequestSpecification getRequestSpecification() {
        request = RestAssured.given();
        request.contentType("application/json");
        return request;
    }

    private static void addAuth() {
        if (TokenGlobal.TOKEN != null && !TokenGlobal.TOKEN.isEmpty()) {
            // For Restful Booker, using Cookie "token". Change to Header "Authorization" for others.
            request.cookie("token", TokenGlobal.TOKEN);
            // request.header("Authorization", "Bearer " + TokenGlobal.TOKEN); 
        }
    }

    @Step("Send GET request to {0}")
    public static Response get(String path) {
        LogUtils.info("GET Request: " + path);
        return getRequestSpecification().get(path);
    }

    @Step("Send GET request to {0} with params {1}")
    public static Response get(String path, Map<String, Object> params) {
        LogUtils.info("GET Request: " + path + " with params: " + params);
        return getRequestSpecification().queryParams(params).get(path);
    }

    @Step("Send POST request to {0} with body")
    public static Response post(String path, Object body) {
        LogUtils.info("POST Request: " + path);
        return getRequestSpecification().body(body).post(path);
    }

    @Step("Send PUT request to {0} with body")
    public static Response put(String path, Object body) {
        LogUtils.info("PUT Request: " + path);
        RequestSpecification req = getRequestSpecification();
        addAuth();
        return req.body(body).put(path);
    }

    @Step("Send PATCH request to {0} with body")
    public static Response patch(String path, Object body) {
        LogUtils.info("PATCH Request: " + path);
        RequestSpecification req = getRequestSpecification();
        addAuth();
        return req.body(body).patch(path);
    }

    @Step("Send DELETE request to {0}")
    public static Response delete(String path) {
        LogUtils.info("DELETE Request: " + path);
        RequestSpecification req = getRequestSpecification();
        addAuth();
        return req.delete(path);
    }
}
