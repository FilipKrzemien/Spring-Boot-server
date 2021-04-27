package com.example.server.models;

import com.example.server.models.db.SubCategory;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@JsonPropertyOrder({"userId", "percentage", "startDate", "endDate", "status", "type", "subCategoryId", "categoryId", "description"})
public class GoalDTO {

    @Getter
    @Setter
    private int userId;

    @Getter
    @Setter
    private Float percentage;

    @Getter
    @Setter
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;

    @Getter
    @Setter
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;

    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private int subCategoryId;

    @Getter
    @Setter
    private int categoryId;

    @Getter
    @Setter
    private String description;
}
