package com.sales.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "sale")
@Getter
@Setter
public class Sale extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "client_id", insertable = false, updatable = false)
    private UUID clientId;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    @Where(clause = "deleted = false")
    private List<Transaction> transactions = new ArrayList<>();

    @Column(name = "seller")
    private String seller;

    @Column(name = "total")
    private BigDecimal total;

    public void addTransaction(Transaction transaction) {
        transaction.setSale(this);
        this.transactions.add(transaction);
    }
}
