package com.example.server.models;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@JsonPropertyOrder({"spendingId", "spendingDetailsId"})
public class DeleteSpendingDTO {

    @Getter
    @Setter
    private int spendingId;

    @Getter
    @Setter
    private int spendingDetailsId;

}
