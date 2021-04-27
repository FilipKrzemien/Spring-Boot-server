package com.example.server.services;

import com.example.server.models.FriendListDTO;
import com.example.server.models.LoggedInUserDTO;
import com.example.server.models.NewFriendRequestDTO;
import com.example.server.models.UpdateRelationshipDTO;
import com.example.server.models.db.Relationship;
import com.example.server.models.db.User;
import com.example.server.repository.RelationshipRepository;
import com.example.server.repository.UsersRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class RelationshipService {

    @Autowired
    RelationshipRepository relationshipRepository;

    @Autowired
    UsersRepository usersRepository;

    public ResponseEntity<?> friendList(LoggedInUserDTO user) {
        User userDAO = new User();
        userDAO.setId(user.getUserId());
        userDAO.setNickname(user.getNickname());
        return new ResponseEntity<>(new FriendListDTO(user.getUserId(), relationshipRepository.findByUserOne(userDAO)), HttpStatus.OK);
    }

    public ResponseEntity<?> newFriendRequest(NewFriendRequestDTO friendRequestDTO) throws JSONException{
        Optional<User> friend = Optional.of(usersRepository.findByNickname(friendRequestDTO.getFriendNickname()));
        if(friend.isEmpty()){
            return new ResponseEntity<>("Couldn't find user with provided nickname", HttpStatus.BAD_REQUEST);
        }
        else {
            User userOne = new User();
            userOne.setId(friendRequestDTO.getUserId());
            Optional<Relationship> relationship = relationshipRepository.findByUserOneAndUserTwo(userOne, friend.get());
            if(relationship.isEmpty()) {
                Relationship newRelationshipOne = new Relationship();
                newRelationshipOne.setUserOne(userOne);
                newRelationshipOne.setLastActionUser(userOne.getId());
                newRelationshipOne.setUserTwo(friend.get());
                newRelationshipOne.setStatus("S");

                relationshipRepository.save(newRelationshipOne);

                Relationship newRelationshipTwo = new Relationship();
                newRelationshipTwo.setUserOne(friend.get());
                newRelationshipTwo.setUserTwo(userOne);
                newRelationshipTwo.setLastActionUser(userOne.getId());
                newRelationshipTwo.setStatus("W");
                relationshipRepository.save(newRelationshipTwo);
                JSONObject resp = new JSONObject();

                resp.put("Status", "ok");
                return new ResponseEntity<>(resp.toString(), HttpStatus.OK);
            }
            else {
                JSONObject resp = new JSONObject();

                resp.put("Status", "Already in friends");
                return new ResponseEntity<>(resp.toString(), HttpStatus.BAD_REQUEST);
            }
        }
    }

    public ResponseEntity<?> updateRelationship(UpdateRelationshipDTO updateRelationshipDTO) {
        User userOne = new User();
        userOne.setId(updateRelationshipDTO.getUserOneId());
        relationshipRepository.updateRelationship(updateRelationshipDTO.getUserOneId(),
                updateRelationshipDTO.getUserTwoId(),
                updateRelationshipDTO.getLastActionUserId(),
                updateRelationshipDTO.getStatus());

        relationshipRepository.updateRelationship(updateRelationshipDTO.getUserTwoId(),
                updateRelationshipDTO.getUserOneId(),
                updateRelationshipDTO.getLastActionUserId(),
                updateRelationshipDTO.getStatus());

        return new ResponseEntity<>(new FriendListDTO(userOne.getId(), relationshipRepository.findByUserOne(userOne)), HttpStatus.OK);
    }

    public ResponseEntity<?> removeRelationship(int id) throws JSONException {

        Optional<Relationship> optRel = relationshipRepository.findById(id);
        Relationship rl1 = optRel.orElseThrow(()->new RuntimeException("Relationship not found"));
        Optional<Relationship> optRel2 = relationshipRepository.findByUserOneAndUserTwo(rl1.getUserTwo(), rl1.getUserOne());
        Relationship rl2 = optRel2.orElseThrow(()->new RuntimeException("Relationship not found"));

        relationshipRepository.delete(rl1);
        relationshipRepository.delete(rl2);

        JSONObject resp = new JSONObject();

        resp.put("Status", "Removed");
        return new ResponseEntity<>(resp.toString(), HttpStatus.OK);
    }
}
