package com.example.server.controllers;

import com.example.server.models.SpendingDetailsDTO;
import com.example.server.models.db.SpendingDetails;
import com.example.server.services.SpendingDetailsService;
import com.sun.istack.NotNull;
import io.vavr.control.Try;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spendingDetails")
@Log4j2
public class SpendingDetailsController {

    @Autowired
    SpendingDetailsService spendingDetailsService;

    @PutMapping("/changeSpendingDetailStatus")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> changeSpendingDetailsStatus(@RequestBody @NotNull SpendingDetailsDTO spendingDetailsDTO) {
        return Try.of(() -> spendingDetailsService.changeSpendingDetailsStatus(spendingDetailsDTO)).getOrElseGet(t -> {
            log.error("Exception. {}", t.getCause().getMessage());
            return null;
        });
    }
}
