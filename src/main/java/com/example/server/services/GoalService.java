package com.example.server.services;

import com.example.server.models.dao.GoalDAO;
import com.example.server.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalService {
    @Autowired
    GoalRepository goalRepository;
    public List<GoalDAO> getGoalByID(int id) {
        return goalRepository.findByID(id);
    }
}
