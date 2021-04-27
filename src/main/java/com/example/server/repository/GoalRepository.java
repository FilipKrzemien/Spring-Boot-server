package com.example.server.repository;

import com.example.server.models.db.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal,Integer> {

    @Query(value = "SELECT * from goal g where g.user_id = ?1", nativeQuery = true)
    List<Goal> findByUserId(int id);


}
