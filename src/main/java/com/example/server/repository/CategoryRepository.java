package com.example.server.repository;

import com.example.server.models.dao.CategoryDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryDAO, Integer> {

    CategoryDAO findByName(String name);
}

