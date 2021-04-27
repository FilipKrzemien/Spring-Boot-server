package com.example.server.controllers;


import com.example.server.models.CategoryDTO;
import com.example.server.models.SubCategoriesDTO;
import com.example.server.models.db.Category;
import com.example.server.services.CategoryService;
import com.example.server.services.SubCategoryService;
import io.vavr.control.Try;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/category")
@Log4j2
public class CategoryController  {

    @Autowired
    CategoryService categoryService;

    @Autowired
    SubCategoryService subCategoryService;

    @GetMapping("/all")
    public List<Category> getAll(){
        return categoryService.getAll();
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> newCategory(@RequestBody @NotNull CategoryDTO categoryDTO){
        return Try.of(() -> categoryService.createCategory(categoryDTO)).getOrElseGet(t ->{
            log.error("Exception. {}",t.getCause().getMessage());
            return null;
        });
    }

    @GetMapping("/initializeCategories")
    public SubCategoriesDTO initializeCategories(){
        return subCategoryService.initializeCategories();
    }
}
