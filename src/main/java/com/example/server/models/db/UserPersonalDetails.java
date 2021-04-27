package com.example.server.models.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_personal_details")
public class UserPersonalDetails {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "personal_details_id")
    private int personalDetailsID;

    @Getter
    @Setter
    @Column(name = "sex")
    private String sex;

    @Getter
    @Setter
    @Column(name = "city")
    private String city;

    @Getter
    @Setter
    @Column(name = "age")
    private int age;

    @Getter
    @Setter
    @Column(name = "first_name")
    private String firstName;

    @Getter
    @Setter
    @Column(name = "last_name")
    private String lastName;

    @Getter
    @Setter
    @OneToOne
    @JsonBackReference
    @JoinColumn(name="user_id",referencedColumnName = "user_id", nullable = false)
    private User user;

    public UserPersonalDetails() {
    }
}
