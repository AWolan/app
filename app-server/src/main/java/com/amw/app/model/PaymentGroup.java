package com.amw.app.model;

import javax.persistence.Entity;

/**
 * Gathers information about group of payments.
 */
@Entity
public class PaymentGroup {

    private Long id;
    private String name;

    public PaymentGroup() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}