package com.example.server.models.db;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="goal")
public class Goal {

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
    private User user;

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
    @Column(name="percentage")
    private Float percentage;

    @Getter
    @Setter
    @Column(name="description")
    private String description;

    @Getter
    @Setter
    @Column(name="goal_start")
    private LocalDate start;

    @Getter
    @Setter
    @Column(name="goal_end")
    private LocalDate end;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name="category_id",referencedColumnName = "category_id")
    private Category category;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name="sub_category_id",referencedColumnName = "sub_category_id")
    private SubCategory subCategory;
}
