package com.example.server.models.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="goal")
public class GoalDAO {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="goal_id")
    private int id;

    @Getter
    @Setter
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name="user_id",referencedColumnName = "user_id",nullable = false)
    private UserDAO userDAO;

    @Getter
    @Setter
    @Column(name="type")
    private String type;

    @Getter
    @Setter
    @Column(name="status")
    private String status;

    @Getter
    @Setter
    @Column(name="name")
    private String name;

    @Getter
    @Setter
    @Column(name="description")
    private String description;

    @Getter
    @Setter
    @Column(name="goal_start")
    private Date start;

    @Getter
    @Setter
    @Column(name="goal_end")
    private Date end;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name="category_id",referencedColumnName = "category_id")
    private CategoryDAO categoryDAO;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name="sub_category_id",referencedColumnName = "sub_category_id")
    private SubCategoryDAO subCategoryDAO;
}
