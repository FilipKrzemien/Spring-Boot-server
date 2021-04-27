package com.example.server.services;

import com.example.server.models.LimitDTO;
import com.example.server.models.db.Category;
import com.example.server.models.db.Limit;
import com.example.server.models.db.SubCategory;
import com.example.server.models.db.User;
import com.example.server.repository.LimitRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LimitService {

    @Autowired
    LimitRepository limitRepository;

    public List<Limit> getLimitByID(int id) {
        User user = new User();
        user.setId(id);
        return limitRepository.findByUserOrderByStartDesc(user);
    }

    public ResponseEntity<?> addLimit(LimitDTO limitDTO) {
        User user = new User();
        user.setId(limitDTO.getUserId());

        Category category = new Category();
        category.setId(limitDTO.getCategoryId());

        SubCategory subCategory = new SubCategory();
        subCategory.setId(limitDTO.getSubCategoryId());
        subCategory.setCategory(category);

        Limit limit = new Limit();
        limit.setCategory(category);
        limit.setSubCategory(subCategory);
        limit.setUser(user);
        limit.setStart(limitDTO.getStartDate());
        limit.setEnd(limitDTO.getEndDate());
        limit.setValue(limitDTO.getValue());

        limitRepository.save(limit);
        JSONObject resp = new JSONObject();
        try {
            resp.put("Status", "ok");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(resp.toString(), HttpStatus.OK);
    }

    public ResponseEntity<?> deleteLimit(int id) throws JSONException {
        Limit limit = limitRepository.findById(id).orElseThrow(()->new RuntimeException("Limit not found"));

        limitRepository.delete(limit);

        JSONObject resp = new JSONObject();

        resp.put("Status", "Removed");
        return new ResponseEntity<>(resp.toString(), HttpStatus.OK);
    }
}
