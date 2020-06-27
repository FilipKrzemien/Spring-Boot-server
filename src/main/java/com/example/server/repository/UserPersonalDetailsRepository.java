package com.example.server.repository;

import com.example.server.models.dao.UserPersonalDetailsDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPersonalDetailsRepository extends JpaRepository<UserPersonalDetailsDAO, Integer> {
}
