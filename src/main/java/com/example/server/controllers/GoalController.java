package com.example.server.controllers;

import com.example.server.models.dao.GoalDAO;
import com.example.server.services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/goal")
public class GoalController {

    @Autowired
    GoalService goalService;

    @GetMapping("/id/{id}")
    public List<GoalDAO> findGoalByUserID(@PathVariable int id){
        return goalService.getGoalByID(id);
    }
}
