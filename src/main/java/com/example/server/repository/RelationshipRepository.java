package com.example.server.repository;

import com.example.server.models.db.Relationship;
import com.example.server.models.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship,Integer> {

    List<Relationship> findByUserOne(User user);

    Optional<Relationship> findByUserOneAndUserTwo(User userOne, User userTwo);

    @Transactional
    @Modifying
    @Query(value = "update relationship set last_action_user =?3, status = ?4 where user_one_id=?1 and user_two_id=?2", nativeQuery = true)
    void updateRelationship(int userOneId, int userTwoId, int lastActionUser, String status);
}
