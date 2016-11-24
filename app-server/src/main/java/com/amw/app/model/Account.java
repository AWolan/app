package com.amw.app.model;

import javax.persistence.*;
import java.util.Currency;

/**
 * Gethers information about account.
 */
@Entity
@Table(name = "t_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // TODO what with account where there is more than one owner?
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Person owner;
    @OneToOne
    private AccountNumber number;
    private Currency currency;

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public AccountNumber getNumber() {
        return number;
    }

    public void setNumber(AccountNumber number) {
        this.number = number;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
