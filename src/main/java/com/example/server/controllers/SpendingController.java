package com.example.server.controllers;

import com.example.server.models.dao.SpendingDAO;
import com.example.server.services.SpendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spending")
public class SpendingController {

    @Autowired
    SpendingService spendingService;

    @GetMapping("/id/{id}")
    List<SpendingDAO> getSpendingsByID(@PathVariable int id){
        return spendingService.findSpendingsByUserID(id);
    }

}
