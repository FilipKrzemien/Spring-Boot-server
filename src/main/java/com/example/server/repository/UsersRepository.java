package com.example.server.repository;

import com.example.server.models.dao.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UserDAO, Integer> {

    UserDAO findById(int id);
    UserDAO findByNickname(String nickname);
}
