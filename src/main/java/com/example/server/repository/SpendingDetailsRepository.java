package com.example.server.repository;

import com.example.server.models.dao.SpendingDetailsDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpendingDetailsRepository extends JpaRepository<SpendingDetailsDAO, Integer> {
}
