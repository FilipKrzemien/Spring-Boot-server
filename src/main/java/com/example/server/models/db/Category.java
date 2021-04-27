package com.example.server.models.db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="category")
public class Category {

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

    public Category() {
    }
}
