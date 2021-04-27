package com.example.server.services;

import com.example.server.models.DeleteSpendingDTO;
import com.example.server.models.SimpleSpendingDetailsDTO;
import com.example.server.models.SpendingDTO;
import com.example.server.models.SpendingDetailsDTO;
import com.example.server.models.db.Spending;
import com.example.server.models.db.SpendingDetails;
import com.example.server.models.db.SubCategory;
import com.example.server.models.db.User;
import com.example.server.repository.SpendingRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SpendingService {

    @Autowired
    SpendingRepository spendingRepository;


    public List<Spending> findSpendingsByUserID(int id) {
        return filterSpendingData(spendingRepository.findByUserID(id));
    }

    private List<Spending> filterSpendingData(List<Spending> byUserID) {
        byUserID.forEach(spending -> {
            User creator = spending.getCreator();
            User filteredCreator = new User(creator.getId(), creator.getNickname());
            spending.setCreator(filteredCreator);
            List<SpendingDetails> details = spending.getSpendingDetails();
            details.forEach(detail -> {
                User user = detail.getUser();
                User filtered = new User(user.getId(), user.getNickname());
                detail.setUser(filtered);
            });
            List<User> filteredUsers = spending.getUsers().stream().map(
                    u -> new User(u.getId(), u.getNickname())
            ).collect(Collectors.toList());
            spending.setUsers(filteredUsers);
        });
        return byUserID;
    }

    public ResponseEntity<?> insertSpending(SpendingDTO spendingDTO) throws JSONException {
        String title = spendingDTO.getTitle();
        User creator = new User();
        creator.setId(spendingDTO.getCreator());
        SubCategory subCategory = spendingDTO.getSubCategory();
        LocalDate date = spendingDTO.getDate();
        ArrayList<SimpleSpendingDetailsDTO> spendingDetailsDTOS = spendingDTO.getUsersWithAmounts();
        String description = spendingDTO.getDescription();
        Spending insert = new Spending();

        insert.setTitle(title);
        insert.setCreator(creator);
        insert.setCategory(subCategory.getCategory());
        insert.setSubCategory(subCategory);
        insert.setDate(date);
        insert.setDescription(description);

        spendingDetailsDTOS.forEach(detail -> {
            SpendingDetails spendingDetails = new SpendingDetails();
            User user = new User();
            user.setId(detail.getUserId());
            if (detail.getUserId() == creator.getId()) spendingDetails.setAccepted(true);
            else spendingDetails.setAccepted(false);
            spendingDetails.setUser(user);
            spendingDetails.setAmount(detail.getAmount());
            insert.addSpendingDetails(spendingDetails);
            insert.addUser(user);
        });

        spendingRepository.save(insert);
        JSONObject resp = new JSONObject();

        resp.put("Status", "ok");

        return new ResponseEntity<>(resp.toString(), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> deleteSpending(int spendingId, int spendingDetailsId) throws JSONException{

        Spending spending1 = spendingRepository.findById(spendingId).orElseThrow(() ->
                new RuntimeException("Spending not found"));

        SpendingDetails spendingDetails = spending1.getSpendingDetails().stream().filter(detail ->
                detail.getSpendingDetailsID() == spendingDetailsId).findAny().orElseThrow(() ->
                new RuntimeException("Spending details not found"));

        spending1.getSpendingDetails().remove(spendingDetails);
        spending1.getUsers().remove(spendingDetails.getUser());
        spendingRepository.save(spending1);

        if (spending1.getSpendingDetails().size() == 0) {
            spendingRepository.delete(spending1);
        }
        JSONObject resp = new JSONObject();

        resp.put("Status", "Removed");

        return new ResponseEntity<>(resp.toString(), HttpStatus.OK);
    }
}
