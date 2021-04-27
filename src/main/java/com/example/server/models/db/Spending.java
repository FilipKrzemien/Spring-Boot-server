package com.example.server.models.db;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="spending")
public class Spending {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spending_sequence")
    @SequenceGenerator(name = "spending_sequence", sequenceName = "spending_sequence", allocationSize = 1)
    @Column(name="spending_id")
    private int id;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name="category_id",referencedColumnName = "category_id", nullable = false)
    private Category category;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name="sub_category_id",referencedColumnName = "sub_category_id", nullable = false)
    private SubCategory subCategory;

    @Getter
    @Setter
    @OneToMany(mappedBy = "spending", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SpendingDetails> spendingDetails;

    public void addSpendingDetails(SpendingDetails spendingDetails){
        spendingDetails.setSpending(this);
        this.spendingDetails.add(spendingDetails);
    }

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name="creator_id",referencedColumnName = "user_id")
    private User creator;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
    @JoinTable(
            name="user_spendings",
            joinColumns = @JoinColumn(name="spending_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    )
    private List<User> users;

    public void addUser(User user) {
        users.add(user);
    }

    @Getter
    @Setter
    @DateTimeFormat(pattern="dd-MM-yyyy")
    @Column(name="date")
    private LocalDate date;

    @Getter
    @Setter
    @Column(name="title")
    private String title;

    @Getter
    @Setter
    @Column(name="description")
    private String description;

    public Spending() {
        setSpendingDetails(new ArrayList<SpendingDetails>());
        setUsers(new ArrayList<User>());
    }
}
