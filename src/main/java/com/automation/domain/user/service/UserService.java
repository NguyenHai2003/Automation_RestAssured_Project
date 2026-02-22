package com.automation.domain.user.service;

import com.automation.domain.user.client.UserClient;
import io.restassured.response.Response;

public class UserService {

    private final UserClient userClient = new UserClient();

    public Response getUsers() {
        return userClient.getUsers();
    }
}
