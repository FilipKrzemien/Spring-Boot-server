package com.example.server.repository;

import com.example.server.models.dao.UserLoginDetailsDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginDetailsRepository extends JpaRepository<UserLoginDetailsDAO, Integer>{

    UserLoginDetailsDAO findByEmail(String email);

    UserLoginDetailsDAO findByLoginDetailsID(int id);
}
