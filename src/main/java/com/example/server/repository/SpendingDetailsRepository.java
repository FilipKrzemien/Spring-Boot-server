package com.example.server.repository;

import com.example.server.models.db.SpendingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SpendingDetailsRepository extends JpaRepository<SpendingDetails, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE spending_details SET accepted = ?2 WHERE spending_details.spending_details_id = ?1", nativeQuery = true)
    void updateStatus(int id, boolean status);
}
