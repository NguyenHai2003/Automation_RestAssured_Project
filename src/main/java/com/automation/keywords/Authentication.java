package com.automation.keywords;

import com.automation.domain.auth.service.AuthService;
import com.automation.utils.LogUtils;

public class Authentication {

    private static final AuthService AUTH_SERVICE = new AuthService();

    public static void login() {
        String token = AUTH_SERVICE.loginWithDefaultCredentials();
        LogUtils.info("Token retrieved: " + token);
    }
}
