package com.example.server.models;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@JsonPropertyOrder({"id","accepted","amount","user", "spending_id"})
public class SpendingDetailsDTO {

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private boolean accepted;

    @Getter
    @Setter
    private Float amount;

    @Getter
    @Setter
    private SimpleUserDTO user;

    @Getter
    @Setter
    private int spending_id;
}
