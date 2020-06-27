package com.example.server.models.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="spending_details")
public class SpendingDetailsDAO {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "spending_details_id")
    private int spendingDetailsID;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name="spending_id",referencedColumnName = "spending_id", nullable = false)
    private SpendingDAO spendingDAO;

    @Getter
    @Setter
    @Column(name="amount")
    private float amount;

    @Getter
    @Setter
    @Column(name="date")
    private Timestamp date;

    @Getter
    @Setter
    @Column(name="title")
    private String title;

    @Getter
    @Setter
    @Column(name="description")
    private String description;

    public SpendingDetailsDAO() {
    }
}
