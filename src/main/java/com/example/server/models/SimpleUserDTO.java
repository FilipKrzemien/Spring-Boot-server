package com.example.server.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@JsonPropertyOrder({"id","nickname"})
public class SimpleUserDTO {

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String nickname;
}
