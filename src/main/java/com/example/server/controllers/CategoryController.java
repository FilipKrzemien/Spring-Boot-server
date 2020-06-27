package com.example.server.controllers;


import com.example.server.models.Category;
import com.example.server.models.dao.CategoryDAO;
import com.example.server.services.CategoryService;
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

    @GetMapping("/all")
    public List<CategoryDAO> getAll(){
        return categoryService.getAll();
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> newCategory(@RequestBody @NotNull Category category){
        return Try.of(() -> categoryService.createCategory(category)).getOrElseGet(t ->{
            log.error("Exception. {}",t.getCause().getMessage());
            return null;
        });
    }

}
