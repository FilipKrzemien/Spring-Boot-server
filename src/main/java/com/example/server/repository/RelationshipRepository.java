package com.example.server.repository;

import com.example.server.models.dao.RelationshipDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationshipRepository extends JpaRepository<RelationshipDAO,Integer> {
}
