package com.example.server.models.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="subCategory")
public class SubCategoryDAO {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="sub_category_id")
    private int id;

    @Getter
    @Setter
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="category_id",referencedColumnName = "category_id", nullable = false)
    private CategoryDAO categoryDAO;

    @Getter
    @Setter
    @Column(name="name")
    private String name;

    public SubCategoryDAO() {
    }
}
