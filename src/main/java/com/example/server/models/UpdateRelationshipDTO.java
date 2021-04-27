package com.example.server.models;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@JsonPropertyOrder({"userOneId", "userTwoId", "lastActionUserId", "status", "relationshipId"})
public class UpdateRelationshipDTO {

    @Getter
    @Setter
    private int userOneId;

    @Getter
    @Setter
    private int userTwoId;

    @Getter
    @Setter
    private int lastActionUserId;

    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    private int relationshipId;

}
