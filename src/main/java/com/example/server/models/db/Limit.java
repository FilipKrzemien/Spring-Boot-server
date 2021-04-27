package com.example.server.models.db;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;


@Entity
@Table(name = "limits")
public class Limit {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "limit_id")
    private int id;

    @Getter
    @Setter
    @Column(name = "value")
    private float value;

    @Getter
    @Setter
    @Column(name = "limit_start")
    private LocalDate start;

    @Getter
    @Setter
    @Column(name = "limit_end")
    private LocalDate end;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "sub_category_id", referencedColumnName = "sub_category_id")
    private SubCategory subCategory;

    @Getter
    @Setter
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    public Limit() {
    }

}
