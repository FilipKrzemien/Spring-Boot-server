package com.example.server.models.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="relationship")
public class RelationshipDAO {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "relationship_id")
    private int relationshipID;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_one_id",referencedColumnName = "user_id",nullable = false)
    private UserDAO userDAOOne;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_two_id",referencedColumnName = "user_id",nullable = false)
    private UserDAO userDAOTwo;

    @Getter
    @Setter
    @Column(name="status")
    private String status;

    @Getter
    @Setter
    @Column(name="last_action_user")
    private int lastActionUser;
}
