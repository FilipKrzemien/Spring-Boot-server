package com.example.server.repository;

import com.example.server.models.dao.LimitDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LimitRepository extends JpaRepository<LimitDAO, Integer> {

    @Query(value = "SELECT * from limit l where l.user_id = ?1", nativeQuery = true)
    List<LimitDAO> findByUserID(int id);

}
