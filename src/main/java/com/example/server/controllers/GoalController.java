package com.example.server.controllers;

import  com.example.server.models.GoalDTO;
import com.example.server.models.db.Goal;
import com.example.server.services.GoalService;
import com.sun.istack.NotNull;
import io.vavr.control.Try;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/goal")
public class GoalController {

    @Autowired
    GoalService goalService;

    @GetMapping("/id/{id}")
    public List<Goal> findGoalByUserID(@PathVariable int id){
        return goalService.getGoalByID(id);
    }

    @PostMapping("/new")
    public ResponseEntity<?> newGoal(@RequestBody @NotNull GoalDTO goal) {
        return Try.of(()-> goalService.addGoal(goal)).getOrElseGet(t -> {
            log.error("Exception. {}", t.getCause().getMessage());
            return null;
        });
    }
}
