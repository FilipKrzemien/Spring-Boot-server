package com.example.server.models;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@JsonPropertyOrder({"userId", "friendNickname"})
public class NewFriendRequestDTO {

    @Getter
    @Setter
    private int userId;

    @Getter
    @Setter
    private String friendNickname;
}
