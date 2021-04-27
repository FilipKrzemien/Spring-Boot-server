package com.example.server.models.db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="subCategory")
public class SubCategory {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="sub_category_id")
    private int id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="category_id",referencedColumnName = "category_id", nullable = false)
    private Category category;

    @Getter
    @Setter
    @Column(name="name")
    private String name;

    public SubCategory() {
    }
}
