package com.amw.app.model;

import javax.persistence.Entity;

/**
 * Gathers information about person.
 */
@Entity
public class Person {

    private Long id;
    private Name name;

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
}
