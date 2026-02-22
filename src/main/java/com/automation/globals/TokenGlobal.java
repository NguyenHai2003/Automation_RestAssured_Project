package com.automation.globals;

public class TokenGlobal {
    private TokenGlobal() {
    }

    public static String getToken() {
        return TokenContext.getToken();
    }

    public static void setToken(String token) {
        TokenContext.setToken(token);
    }

    public static void clear() {
        TokenContext.clear();
    }
}
