package com.example.server.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@JsonPropertyOrder({"userId", "startDate", "endDate", "value", "subCategoryId", "categoryId"})
public class LimitDTO {

    @Getter
    @Setter
    int userId;

    @Getter
    @Setter
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate startDate;

    @Getter
    @Setter
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate endDate;

    @Getter
    @Setter
    float value;

    @Getter
    @Setter
    int subCategoryId;

    @Getter
    @Setter
    int categoryId;
}
