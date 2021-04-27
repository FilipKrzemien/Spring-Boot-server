package com.example.server.repository;

import com.example.server.models.db.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Integer> {

    User findById(int id);
    User findByNickname(String nickname);
}
