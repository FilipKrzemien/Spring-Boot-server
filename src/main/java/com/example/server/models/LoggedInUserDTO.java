package com.example.server.models;

import com.example.server.models.db.UserPersonalDetails;
import lombok.Getter;

public class LoggedInUserDTO {

    @Getter
    Integer userId;
    @Getter
    String nickname;


    public LoggedInUserDTO(Integer userId, String nickname) {
        this.userId = userId;
        this.nickname = nickname;
    }
}
