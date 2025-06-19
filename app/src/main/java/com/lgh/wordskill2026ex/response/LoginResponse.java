package com.lgh.wordskill2026ex.response;

import com.lgh.wordskill2026ex.entity.User;

public class LoginResponse {

    private String token;

    private User user;

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }
}
