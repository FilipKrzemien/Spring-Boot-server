package com.example.server.models.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="users")
public class User {

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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user",fetch = FetchType.LAZY)
    private UserPersonalDetails personalDetails;

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user",fetch = FetchType.LAZY)
    private UserLoginDetails loginDetails;

    @Getter
    @Setter
    @JsonBackReference
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Limit> limits;

    @Getter
    @Setter
    @JsonBackReference
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Goal> goals;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="user_spendings",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="spending_id")
    )
    @JsonBackReference
    private List<Spending> spendings;

    public User() {
    }

    public User(int id, String nickname){
        this.id = id;
        this.nickname = nickname;
    }
}
