package com.example.server.models;

import com.example.server.models.db.Relationship;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class FriendListDTO {

    public FriendListDTO(Integer userId, List<Relationship> friendList){
        this.userId = userId;
        this.friendList = friendList;
    }
    @Getter
    @Setter
    Integer userId;

    @Getter
    @Setter
    List<Relationship> friendList;
}
