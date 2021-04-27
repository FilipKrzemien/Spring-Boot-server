package com.example.server.repository;

import com.example.server.models.db.UserLoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginDetailsRepository extends JpaRepository<UserLoginDetails, Integer> {

    UserLoginDetails findByEmail(String email);

    UserLoginDetails findByLoginDetailsID(int id);

    @Query(value = "SELECT * FROM user_login_details u WHERE u.email = ?1 AND u.password = ?2", nativeQuery = true)
    UserLoginDetails login(String email, String password);
}
