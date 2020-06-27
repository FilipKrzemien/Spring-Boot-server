package com.example.server.services;

import com.example.server.models.PasswordChange;
import com.example.server.models.RegistrationForm;
import com.example.server.models.dao.UserDAO;
import com.example.server.models.dao.UserLoginDetailsDAO;
import com.example.server.models.dao.UserPersonalDetailsDAO;
import com.example.server.repository.UserLoginDetailsRepository;
import com.example.server.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserLoginDetailsRepository loginDetailsRepository;

    public List<UserDAO> getAll() {
        return usersRepository.findAll();
    }

    public UserDAO getByID(int id) {
        return usersRepository.findById(id);
    }

    public UserDAO getByNick(String nick) {
        return usersRepository.findByNickname(nick);
    }

    public ResponseEntity<?> registerNewUser(RegistrationForm registrationForm) {

        Optional<UserDAO> userToCheck = Optional.ofNullable(usersRepository.findByNickname(registrationForm.getNickname()));
        Optional<UserLoginDetailsDAO> loginDetailsToCheck = Optional.ofNullable(loginDetailsRepository
                .findByEmail(registrationForm.getEmail()));

        if (userToCheck.isEmpty() && loginDetailsToCheck.isEmpty()) {

            UserDAO insert = prepareUserDB(registrationForm);

            usersRepository.save(insert);
            return new ResponseEntity<>("User created", HttpStatus.CREATED);
        } else if (userToCheck.isPresent()) {
            return new ResponseEntity<>("Nickname is taken", HttpStatus.BAD_REQUEST);
        } else if (loginDetailsToCheck.isPresent()) {
            return new ResponseEntity<>("Email is taken", HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity<>("User creation failed", HttpStatus.BAD_REQUEST);
    }

    private UserDAO prepareUserDB(RegistrationForm registrationForm) {
        UserDAO insert = new UserDAO();
        insert.setNickname(registrationForm.getNickname());
        insert.setPoints(0);

        UserLoginDetailsDAO loginDetails = new UserLoginDetailsDAO();
        loginDetails.setEmail(registrationForm.getEmail());
        loginDetails.setPassword(registrationForm.getPassword());
        loginDetails.setUserDAO(insert);

        UserPersonalDetailsDAO personalDetails = new UserPersonalDetailsDAO();
        personalDetails.setAge(registrationForm.getAge());
        personalDetails.setCity(registrationForm.getCity());
        personalDetails.setSex(registrationForm.getSex());
        personalDetails.setFirstName(registrationForm.getFirstName());
        personalDetails.setLastName(registrationForm.getLastName());
        personalDetails.setUserDAO(insert);

        insert.setLoginDetails(loginDetails);
        insert.setPersonalDetails(personalDetails);
        return insert;
    }

    public ResponseEntity<?> changePassword(PasswordChange passwordChange) {
        Optional<UserLoginDetailsDAO> loginDetailsToCheck = Optional.ofNullable(loginDetailsRepository
                .findByLoginDetailsID(passwordChange.getId()));

        if (loginDetailsToCheck.isPresent()) {
            if (loginDetailsToCheck.get().getPassword().equals(passwordChange.getOldPassword())) {
                loginDetailsToCheck.get().setPassword(passwordChange.getNewPassword());
                loginDetailsRepository.save(loginDetailsToCheck.get());
                return new ResponseEntity<>("Password changed", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Old password doesn't match", HttpStatus.BAD_REQUEST);
            }

        } else return new ResponseEntity<>("Password change failed", HttpStatus.BAD_REQUEST);
    }
}
