package com.example.server.services;

import com.example.server.models.Category;
import com.example.server.models.dao.CategoryDAO;
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
    public List<CategoryDAO> getAll() {
        return categoryRepository.findAll();
    }

    public ResponseEntity<?> createCategory(Category category) {
        String name = category.getName();

        Optional<CategoryDAO> result = Optional.ofNullable(categoryRepository.findByName(name));

        if(result.isEmpty()){
            CategoryDAO insert = new CategoryDAO();
            insert.setName(name);
            categoryRepository.save(insert);
            return new ResponseEntity<>("Category created", HttpStatus.CREATED);
        }
        else return new ResponseEntity<>("Category already exist", HttpStatus.BAD_REQUEST);
    }
}
