package com.example.server.models;


import com.example.server.deserializer.MapDeserializer;
import com.example.server.models.db.SubCategory;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@JsonPropertyOrder({"title", "subCategory", "date", "creator", "usersWithAmounts", "description"})
public class SpendingDTO {

    @Getter
    @Setter
    String title;

    @Getter
    @Setter
    SubCategory subCategory;

    @Getter
    @Setter
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate date;

    @Getter
    @Setter
    Integer creator;

    @Getter
    @Setter
    ArrayList<SimpleSpendingDetailsDTO> usersWithAmounts;

    @Getter
    @Setter
    String description;
}
