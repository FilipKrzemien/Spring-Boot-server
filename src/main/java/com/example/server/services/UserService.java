package com.example.server.services;

import com.example.server.models.LoggedInUserDTO;
import com.example.server.models.LoginFormDTO;
import com.example.server.models.PasswordChangeDTO;
import com.example.server.models.RegistrationFormDTO;
import com.example.server.models.db.User;
import com.example.server.models.db.UserLoginDetails;
import com.example.server.models.db.UserPersonalDetails;
import com.example.server.repository.UserLoginDetailsRepository;
import com.example.server.repository.UserPersonalDetailsRepository;
import com.example.server.repository.UsersRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserLoginDetailsRepository loginDetailsRepository;

    @Autowired
    private UserPersonalDetailsRepository personalDetailsRepository;

    public List<User> getAll() {
        return usersRepository.findAll();
    }

    public User getByID(int id) {
        return usersRepository.findById(id);
    }

    public User getByNick(String nick) {
        return usersRepository.findByNickname(nick);
    }

    public ResponseEntity<?> registerNewUser(RegistrationFormDTO registrationFormDTO) throws JSONException {

        Optional<User> userToCheck = Optional.ofNullable(usersRepository.findByNickname(registrationFormDTO.getNickname()));
        Optional<UserLoginDetails> loginDetailsToCheck = Optional.ofNullable(loginDetailsRepository
                .findByEmail(registrationFormDTO.getEmail()));

        if (userToCheck.isEmpty() && loginDetailsToCheck.isEmpty()) {

            User insert = prepareUserDB(registrationFormDTO);

            usersRepository.save(insert);
            JSONObject resp = new JSONObject();
            resp.put("Status", "Account has been created");

            return new ResponseEntity<>(resp.toString(), HttpStatus.OK);
        } else if (userToCheck.isPresent()) {
            return new ResponseEntity<>("Nickname is taken", HttpStatus.BAD_REQUEST);
        } else if (loginDetailsToCheck.isPresent()) {
            return new ResponseEntity<>("Email is taken", HttpStatus.BAD_REQUEST);
        } else{
            return new ResponseEntity<>("User creation failed", HttpStatus.BAD_REQUEST);
        }

    }

    private User prepareUserDB(RegistrationFormDTO registrationFormDTO) {
        User insert = new User();
        insert.setNickname(registrationFormDTO.getNickname());
        insert.setPoints(0);

        UserLoginDetails loginDetails = new UserLoginDetails();
        loginDetails.setEmail(registrationFormDTO.getEmail());
        loginDetails.setPassword(registrationFormDTO.getPassword());
        loginDetails.setUser(insert);

        UserPersonalDetails personalDetails = new UserPersonalDetails();
        personalDetails.setAge(registrationFormDTO.getAge());
        personalDetails.setCity(registrationFormDTO.getCity());
        personalDetails.setSex(registrationFormDTO.getSex());
        personalDetails.setFirstName(registrationFormDTO.getFirstName());
        personalDetails.setLastName(registrationFormDTO.getLastName());
        personalDetails.setUser(insert);

        insert.setLoginDetails(loginDetails);
        insert.setPersonalDetails(personalDetails);
        return insert;
    }

    public ResponseEntity<?> changePassword(PasswordChangeDTO passwordChangeDTO) throws JSONException{
        Optional<UserLoginDetails> loginDetailsToCheck = Optional.ofNullable(loginDetailsRepository
                .findByLoginDetailsID(passwordChangeDTO.getId()));
        JSONObject resp = new JSONObject();
        if (loginDetailsToCheck.isPresent()) {
            if (loginDetailsToCheck.get().getPassword().equals(passwordChangeDTO.getOldPassword())) {
                loginDetailsToCheck.get().setPassword(passwordChangeDTO.getNewPassword());
                loginDetailsRepository.save(loginDetailsToCheck.get());
                resp.put("Status", "Password changed");
                return new ResponseEntity<>(resp.toString(), HttpStatus.CREATED);
            } else {
                resp.put("Status", "Old password doesn't match");
                return new ResponseEntity<>(resp.toString(), HttpStatus.BAD_REQUEST);
            }

        } else {
            resp.put("Status", "Password change failed");
            return new ResponseEntity<>(resp.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> login(LoginFormDTO loginform) {
        Optional<UserLoginDetails> userLoginDetails = Optional.ofNullable(loginDetailsRepository
                .login(loginform.getEmail(), loginform.getPassword()));
        Optional<User> user = userLoginDetails.map(UserLoginDetails::getUser);

        if (user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }
}
