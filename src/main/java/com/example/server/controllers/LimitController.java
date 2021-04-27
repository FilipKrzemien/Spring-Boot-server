package com.example.server.controllers;

import com.example.server.models.LimitDTO;
import com.example.server.models.db.Limit;
import com.example.server.services.LimitService;
import io.vavr.control.Try;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/limit")
@Log4j2
public class LimitController {

    @Autowired
    LimitService limitService;

    @PostMapping("/new")
    public ResponseEntity<?> newLimit(@RequestBody @NotNull LimitDTO limitDTO){
        return Try.of(()-> limitService.addLimit(limitDTO)).getOrElseGet(t -> {
            log.error("Exception. {}", t.getCause().getMessage());
            return null;
        });
    }

    @GetMapping("/id/{id}")
    public List<Limit> findLimitsByUserID(@PathVariable int id){
        return limitService.getLimitByID(id);
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteLimit(@PathVariable int id){
        return Try.of(()-> limitService.deleteLimit(id)).getOrElseGet(t -> {
            log.error("Exception. {}", t.getCause().getMessage());
            return null;
        });
    }

}
