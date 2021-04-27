package com.example.server.controllers;

import com.example.server.models.DeleteSpendingDTO;
import com.example.server.models.SpendingDTO;
import com.example.server.models.db.Spending;
import com.example.server.models.db.SpendingDetails;
import com.example.server.services.SpendingService;
import com.sun.istack.NotNull;
import io.vavr.control.Try;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spending")
@Log4j2
public class SpendingController {

    @Autowired
    SpendingService spendingService;

    @GetMapping("/id/{id}")
    public List<Spending> getSpendingsByID(@PathVariable int id) {
        return spendingService.findSpendingsByUserID(id);
    }


    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> newSpending(@RequestBody @NotNull SpendingDTO spending) {
        return Try.of(() -> spendingService.insertSpending(spending)).getOrElseGet(t -> {
            log.error("Exception. {}", t.getCause().getMessage());
            return null;
        });
    }

    @DeleteMapping("/delete/{spendingId}/spendingDetails/{spendingDetailsId}")
    public ResponseEntity<?> deleteSpending(@PathVariable int spendingId, @PathVariable int spendingDetailsId){
        return Try.of(()-> spendingService.deleteSpending(spendingId, spendingDetailsId)).getOrElseGet(t->{
            log.error("Excetpion. {}", t.getCause().getMessage());
            return null;
        });
    }
}
