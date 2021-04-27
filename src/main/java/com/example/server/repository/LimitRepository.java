package com.example.server.repository;

import com.example.server.models.db.Limit;
import com.example.server.models.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Integer> {

    @Query(value = "SELECT * from limit l where l.user_id = ?1", nativeQuery = true)
    List<Limit> findByUserID(int id);

    List<Limit> findByUserOrderByStartDesc(User user);
}
