package com.example.server.models;

import lombok.Getter;

public class LoggedInUser {

    @Getter
    String userId;
    @Getter
    String nickname;

    public LoggedInUser(String userId, String nickname) {
        this.userId = userId;
        this.nickname = nickname;
    }
}
