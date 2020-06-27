package com.example.server.models.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="category")
public class CategoryDAO {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="category_id")
    private int id;

    @Getter
    @Setter
    @Column(name="name")
    private String name;

    public CategoryDAO() {
    }
}
