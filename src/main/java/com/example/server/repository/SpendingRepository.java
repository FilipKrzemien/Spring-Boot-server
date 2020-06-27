package com.example.server.repository;

import com.example.server.models.dao.SpendingDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpendingRepository extends JpaRepository<SpendingDAO, Integer> {

    @Query(value = "SELECT * from spending s,user_spendings u where s.spending_id=u.spending_id and u.user_id = ?1", nativeQuery = true)
    List<SpendingDAO> findByUserID(int id);
}
