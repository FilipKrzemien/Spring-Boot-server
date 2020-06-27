package com.example.server.services;

import com.example.server.models.dao.LimitDAO;
import com.example.server.repository.LimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LimitService {

    @Autowired
    LimitRepository limitRepository;

    public List<LimitDAO> getLimitByID(int id) {
//    return limitRepository.findByUserID(id);
    return limitRepository.findByUserID(id);
    }
}
