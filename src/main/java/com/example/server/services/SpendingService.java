package com.example.server.services;

import com.example.server.models.dao.SpendingDAO;
import com.example.server.repository.SpendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpendingService {

    @Autowired
    SpendingRepository spendingRepository;


    public List<SpendingDAO> findSpendingsByUserID(int id) {
        return spendingRepository.findByUserID(id);
    }
}
