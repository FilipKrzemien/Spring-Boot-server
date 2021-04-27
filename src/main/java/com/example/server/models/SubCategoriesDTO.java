package com.example.server.models;

import com.example.server.models.db.SubCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class SubCategoriesDTO {

    public SubCategoriesDTO(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    @Getter
    @Setter
    List<SubCategory> subCategories;
}
