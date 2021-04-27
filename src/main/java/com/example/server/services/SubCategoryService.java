package com.example.server.services;

import com.example.server.models.SubCategoriesDTO;
import com.example.server.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubCategoryService {

    @Autowired
    SubCategoryRepository subCategoryRepository;

    public SubCategoriesDTO initializeCategories() {return new SubCategoriesDTO(subCategoryRepository.findAll());}
}
