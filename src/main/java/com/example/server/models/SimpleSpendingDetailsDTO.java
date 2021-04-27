package com.example.server.models;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@JsonPropertyOrder({"userId", "amount"})
public class SimpleSpendingDetailsDTO {

    @Getter
    @Setter
    private int userId;

    @Getter
    @Setter
    private float amount;
}
