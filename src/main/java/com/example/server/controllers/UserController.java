package com.example.server.controllers;

import com.example.server.models.LoginFormDTO;
import com.example.server.models.PasswordChangeDTO;
import com.example.server.models.RegistrationFormDTO;
import com.example.server.models.db.User;
import com.example.server.services.UserService;
import com.sun.istack.NotNull;
import io.vavr.control.Try;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("id/{id}")
    public User getByID(@PathVariable int id) {
        return userService.getByID(id);
    }

    @GetMapping("/nick/{nick}")
    public User getByID(@PathVariable String nick) {
        return userService.getByNick(nick);
    }

    @PostMapping("/new")
    public ResponseEntity<?> registration(@RequestBody @NotNull RegistrationFormDTO registrationFormDTO) {
        return Try.of(() -> userService.registerNewUser(registrationFormDTO)).getOrElseGet(t -> {
            log.error("Exception. {}", t.getCause().getMessage());
            return null;
        });
    }

    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody @NotNull PasswordChangeDTO passwordChangeDTO) {
        return Try.of(() -> userService.changePassword(passwordChangeDTO)).getOrElseGet(t -> {
            log.error("Exception. {}", t.getCause().getMessage());
            return null;
        });
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @NotNull LoginFormDTO loginform) {
        return Try.of(() -> userService.login(loginform)).getOrElseGet(t -> {
            log.error("Exception. {}", t.getCause().getMessage());
            return null;
        });
    }
}
