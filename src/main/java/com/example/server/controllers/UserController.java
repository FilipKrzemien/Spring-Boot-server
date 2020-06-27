package com.example.server.controllers;

import com.example.server.models.PasswordChange;
import com.example.server.models.RegistrationForm;
import com.example.server.models.dao.UserDAO;
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
    public List<UserDAO> getAll(){
        return userService.getAll();
    }

    @GetMapping("id/{id}")
    public UserDAO getByID(@PathVariable int id){
        return userService.getByID(id);
    }

    @GetMapping("/nick/{nick}")
    public UserDAO getByID(@PathVariable String nick) {return userService.getByNick(nick);}

    @PostMapping("/new")
    public ResponseEntity<?> registration(@RequestBody @NotNull RegistrationForm registrationForm){
        return Try.of(() -> userService.registerNewUser(registrationForm)).getOrElseGet(t ->{
            log.error("Exception. {}",t.getCause().getMessage());
            return null;
        });
    }

    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody @NotNull PasswordChange passwordChange){
        return Try.of(() -> userService.changePassword(passwordChange)).getOrElseGet(t ->{
            log.error("Exception. {}",t.getCause().getMessage());
            return null;
        });
    }
}
