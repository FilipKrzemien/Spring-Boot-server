package com.example.server.repository;

import com.example.server.models.db.Spending;
import com.example.server.models.db.SubCategory;
import com.example.server.models.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpendingRepository extends JpaRepository<Spending, Integer> {

    @Query(value = "SELECT * from spending s, spending_details d where s.spending_id=d.spending_id and d.user_id=?1 ORDER BY s.date DESC", nativeQuery = true)
    List<Spending> findByUserID(int id);

    List<Spending> findByUsersInAndSubCategory(List<User> users, SubCategory subCategory);
}
