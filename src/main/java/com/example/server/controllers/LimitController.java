package com.example.server.controllers;

import com.example.server.models.dao.LimitDAO;
import com.example.server.services.LimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/limit")
public class LimitController {

    @Autowired
    LimitService limitService;

    @GetMapping("/id/{id}")
    public List<LimitDAO> findLimitsByUserID(@PathVariable int id){
        return limitService.getLimitByID(id);
    }

}
