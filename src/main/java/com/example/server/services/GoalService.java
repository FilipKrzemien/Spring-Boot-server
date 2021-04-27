package com.example.server.services;

import com.example.server.models.GoalDTO;
import com.example.server.models.db.*;
import com.example.server.repository.GoalRepository;
import com.example.server.repository.SpendingRepository;
import com.example.server.repository.UsersRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class GoalService {
    @Autowired
    GoalRepository goalRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    SpendingRepository spendingRepository;

    public List<Goal> getGoalByID(int id) {
        return goalRepository.findByUserId(id);
    }

    public ResponseEntity<?> addGoal(GoalDTO goal) {
        Goal goalToInsert = new Goal();
        goalToInsert.setPercentage(goal.getPercentage());
        goalToInsert.setDescription(goal.getDescription());
        goalToInsert.setStart(goal.getStartDate());
        goalToInsert.setEnd(goal.getEndDate());
        goalToInsert.setType(goal.getType());
        goalToInsert.setStatus(goal.getStatus());

        User user = new User();
        user.setId(goal.getUserId());
        SubCategory subCategory = new SubCategory();
        subCategory.setId(goal.getSubCategoryId());
        Category category = new Category();
        category.setId(goal.getCategoryId());
        goalToInsert.setUser(user);
        goalToInsert.setCategory(category);
        goalToInsert.setSubCategory(subCategory);

        goalRepository.save(goalToInsert);
        JSONObject resp = new JSONObject();
        try {
            resp.put("Status", "ok");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(resp.toString(), HttpStatus.OK);
    }

    @Scheduled(cron = "0 0 0 1 1/1 *")
    public void calculatePoints() {
        List<User> users = usersRepository.findAll();
        for (User u : users) {
            List<Goal> goals = goalRepository.findByUserId(u.getId());
            for (Goal g : goals) {
                if (g.getStatus().equals("P")) {
                    handleConcreteGoal(u, g);
                }
            }
        }
    }

    private void handleConcreteGoal(User u, Goal g) {
        LocalDate now = LocalDate.now();
        if (g.getEnd().isBefore(now)) {
            List<SpendingDetails> userSpendingDetailsInPreviousBillingPeriod;
            SubCategory goalSubCategory = g.getSubCategory();
            List<Spending> userSpendingsInCategory = spendingRepository.findByUsersInAndSubCategory(Collections.singletonList(u), goalSubCategory);

            List<SpendingDetails> userSpendingDetailsInCurrentBillingPeriod = userSpendingsInCategory
                    .stream()
                    .filter(spending -> spending.getDate().isBefore(g.getEnd()) && spending.getDate().isAfter(g.getStart()))
                    .map(Spending::getSpendingDetails)
                    .flatMap(Collection::stream)
                    .filter(spendingDetails -> spendingDetails.getUser().getId() == u.getId())
                    .collect(toList());
            if (g.getType().equals("Monthly")) {
                userSpendingDetailsInPreviousBillingPeriod = userSpendingsInCategory
                        .stream()
                        .filter(spending -> spending.getDate().isBefore(g.getStart()) && spending.getDate().isAfter(g.getStart().minusMonths(1)))
                        .map(Spending::getSpendingDetails)
                        .flatMap(Collection::stream)
                        .filter(spendingDetails -> spendingDetails.getUser().getId() == u.getId())
                        .collect(toList());
            } else {
                userSpendingDetailsInPreviousBillingPeriod = userSpendingsInCategory
                        .stream()
                        .filter(spending -> spending.getDate().isBefore(g.getStart()) && spending.getDate().isAfter(g.getStart().minusYears(1)))
                        .map(Spending::getSpendingDetails)
                        .flatMap(Collection::stream)
                        .filter(spendingDetails -> spendingDetails.getUser().getId() == u.getId())
                        .collect(toList());
            }
            Float currentBillingPeriodSum = (float) userSpendingDetailsInCurrentBillingPeriod
                    .stream()
                    .mapToDouble(SpendingDetails::getAmount)
                    .sum();

            Float previousBillingPeriodSum = (float) userSpendingDetailsInPreviousBillingPeriod
                    .stream()
                    .mapToDouble(SpendingDetails::getAmount)
                    .sum();

            Float difference = previousBillingPeriodSum / currentBillingPeriodSum;

            if (difference > g.getPercentage()) {
                g.setStatus("F");
                if (g.getType().equals("Monthly")) {
                    u.setPoints(u.getPoints() - 10);
                } else if (g.getType().equals("Yearly")) {
                    u.setPoints(u.getPoints() - 100);
                }
            } else {
                g.setStatus("S");
                if (g.getType().equals("Monthly")) {
                    u.setPoints(u.getPoints() + 10);
                } else if (g.getType().equals("Yearly")) {
                    u.setPoints(u.getPoints() + 100);
                }
            }
            goalRepository.save(g);
            usersRepository.save(u);
        }
    }
}
