package com.example.server.models.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="user_login_details")
public class UserLoginDetailsDAO {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "login_details_id")
    private int loginDetailsID;

    @Getter
    @Setter
    @Column(name="email")
    private String email;

    @Getter
    @Setter
    @Column(name="password")
    private String password;

    @Getter
    @Setter
    @OneToOne
    @JsonBackReference
    @JoinColumn(name="user_id",referencedColumnName = "user_id", nullable = false)
    private UserDAO userDAO;

    public UserLoginDetailsDAO() {
    }
}
