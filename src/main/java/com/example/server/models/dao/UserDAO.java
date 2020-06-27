package com.example.server.models.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="users")
public class UserDAO {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private int id;

    @Getter
    @Setter
    @Column(name="nickname", unique = true)
    private String nickname;

    @Getter
    @Setter
    @Column(name="points")
    private int points;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "userDAO",fetch = FetchType.LAZY)
    private UserPersonalDetailsDAO personalDetails;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "userDAO",fetch = FetchType.LAZY)
    private UserLoginDetailsDAO loginDetails;

    @Getter
    @Setter
    @JsonBackReference
    @OneToMany(mappedBy = "userDAO",fetch = FetchType.LAZY)
    private List<LimitDAO> limitDAOS;

    @Getter
    @Setter
    @JsonBackReference
    @OneToMany(mappedBy = "userDAO",fetch = FetchType.LAZY)
    private List<GoalDAO> goalDAOS;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="user_spendings",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="spending_id")
    )
    @JsonBackReference
    private List<SpendingDAO> spendingDAOS;
    public UserDAO() {
    }
}
