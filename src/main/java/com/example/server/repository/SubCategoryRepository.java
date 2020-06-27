package com.example.server.repository;

import com.example.server.models.dao.SubCategoryDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategoryDAO, Integer> {
}
