package com.example.server.controllers;


import com.example.server.models.LoggedInUserDTO;
import com.example.server.models.NewFriendRequestDTO;
import com.example.server.models.UpdateRelationshipDTO;
import com.example.server.services.RelationshipService;
import io.vavr.control.Try;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@Log4j2
@RequestMapping("relationship")
public class RelationshipController {

    @Autowired
    RelationshipService relationshipService;


    @PostMapping("/friendList")
    public ResponseEntity<?> userFriendList(@RequestBody @NotNull LoggedInUserDTO user) {
        return Try.of(() -> relationshipService.friendList(user)).getOrElseGet(t -> {
            log.error("Exception {}", t.getCause().getCause());
            return null;
        });
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateRelationship(@RequestBody @NotNull UpdateRelationshipDTO updateRelationshipDTO){
        return Try.of(() -> relationshipService.updateRelationship(updateRelationshipDTO)).getOrElseGet(t -> {
            log.error("Exception {}", t.getCause().getCause());
            return null;
        });
    }

    @PostMapping("/new")
    public ResponseEntity<?> newFriend(@RequestBody @NotNull NewFriendRequestDTO friendRequestDTO) {
        return Try.of(() -> relationshipService.newFriendRequest(friendRequestDTO)).getOrElseGet(t -> {
            log.error("Exception {}", t.getCause().getCause());
            return null;
        });
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> removeRelationship(@PathVariable int id) {
        return Try.of(() -> relationshipService.removeRelationship(id)).getOrElseGet(t -> {
            log.error("Exception {}", t.getCause().getCause());
            return null;
        });
    }
}
