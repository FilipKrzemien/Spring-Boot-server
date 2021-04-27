package com.example.server.models.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "spending_details")
public class SpendingDetails {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spending_details_sequence")
    @SequenceGenerator(name = "spending_details_sequence", sequenceName = "spending_details_sequence", allocationSize = 1)
    @Column(name = "spending_details_id")
    private int spendingDetailsID;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "spending_id", referencedColumnName = "spending_id")
    private Spending spending;

    @Getter
    @Setter
    @Column(name = "amount")
    private float amount;

    @Getter
    @Setter
    @Column(name = "accepted")
    private boolean accepted;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    public SpendingDetails() {
    }
}
