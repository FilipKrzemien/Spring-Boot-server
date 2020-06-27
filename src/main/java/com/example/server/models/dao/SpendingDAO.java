package com.example.server.models.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="spending")
public class SpendingDAO {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="spending_id")
    private int id;

    @Getter
    @Setter
    @Column(name="accepted")
    private boolean accepted;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name="category_id",referencedColumnName = "category_id", nullable = false)
    private CategoryDAO categoryDAO;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name="sub_category_id",referencedColumnName = "sub_category_id", nullable = false)
    private SubCategoryDAO subCategoryDAO;

    @Getter
    @Setter
    @OneToOne(mappedBy = "spendingDAO",fetch = FetchType.LAZY)
    private SpendingDetailsDAO spendingDetailsDAO;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name="creator_id",referencedColumnName = "user_id")
    private UserDAO creator;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "spendingDAOS", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<UserDAO> userDAOS;
    public SpendingDAO() {
    }
}
