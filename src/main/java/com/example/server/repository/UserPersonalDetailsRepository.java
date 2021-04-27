package com.example.server.repository;

import com.example.server.models.db.User;
import com.example.server.models.db.UserPersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPersonalDetailsRepository extends JpaRepository<UserPersonalDetails, Integer> {
    UserPersonalDetails findByUser(Optional<User> user);
}
