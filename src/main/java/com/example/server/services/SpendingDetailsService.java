package com.example.server.services;

import com.example.server.models.SpendingDetailsDTO;
import com.example.server.models.db.Spending;
import com.example.server.models.db.SpendingDetails;
import com.example.server.models.db.User;
import com.example.server.repository.SpendingDetailsRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SpendingDetailsService {

    @Autowired
    SpendingDetailsRepository spendingDetailsRepository;

    public ResponseEntity<?> changeSpendingDetailsStatus(SpendingDetailsDTO spendingDetailsDTO) throws JSONException {
        SpendingDetails spendingDetails = new SpendingDetails();
        User user = new User();
        user.setId(spendingDetailsDTO.getUser().getId());
        spendingDetails.setUser(user);
        spendingDetails.setSpendingDetailsID(spendingDetailsDTO.getId());
        spendingDetails.setAccepted(spendingDetailsDTO.isAccepted());
        spendingDetails.setAmount(spendingDetailsDTO.getAmount());
        Spending spending = new Spending();
        spending.setId(spendingDetailsDTO.getSpending_id());
        spending.addSpendingDetails(spendingDetails);
        spending.addUser(user);

        spendingDetailsRepository.updateStatus(spendingDetails.getSpendingDetailsID(), spendingDetails.isAccepted());

        JSONObject resp = new JSONObject();
        resp.put("Status", "Status updated");
        return new ResponseEntity<>(resp.toString(), HttpStatus.OK);
    }
}
