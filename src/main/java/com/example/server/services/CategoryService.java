package com.example.server.services;

import com.example.server.models.CategoryDTO;
import com.example.server.models.db.Category;
import com.example.server.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public ResponseEntity<?> createCategory(CategoryDTO categoryDTO) {
        String name = categoryDTO.getName();

        Optional<Category> result = Optional.ofNullable(categoryRepository.findByName(name));

        if(result.isEmpty()){
            Category insert = new Category();
            insert.setName(name);
            categoryRepository.save(insert);
            return new ResponseEntity<>("Category created", HttpStatus.CREATED);
        }
        else return new ResponseEntity<>("Category already exists", HttpStatus.BAD_REQUEST);
    }
}
