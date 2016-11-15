package com.amw.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Currency;

/**
 * Gethers information about account.
 */
@Entity
public class Account {

    @Id
    private Long id;
    // TODO what with account where there is more than one owner?
    private Person owner;
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
