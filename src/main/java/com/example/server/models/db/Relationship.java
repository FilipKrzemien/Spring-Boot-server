package com.example.server.models.db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="relationship")
public class Relationship {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "relationship_id")
    private int relationshipId;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_one_id",referencedColumnName = "user_id",nullable = false)
    private User userOne;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_two_id",referencedColumnName = "user_id",nullable = false)
    private User userTwo;

    @Getter
    @Setter
    @Column(name="status")
    private String status;

    @Getter
    @Setter
    @Column(name="last_action_user")
    private int lastActionUser;
}
