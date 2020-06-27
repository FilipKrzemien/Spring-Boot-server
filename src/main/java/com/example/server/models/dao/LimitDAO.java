package com.example.server.models.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name="limits")
public class LimitDAO {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="limit_id")
    private int id;

    @Getter
    @Setter
    @Column(name="value")
    private float value;

    @Getter
    @Setter
    @Column(name="limit_start")
    private Date start;

    @Getter
    @Setter
    @Column(name="limit_end")
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

    @Getter
    @Setter
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "user_id", nullable = false)
    private UserDAO userDAO;

    public LimitDAO() {
    }

}
