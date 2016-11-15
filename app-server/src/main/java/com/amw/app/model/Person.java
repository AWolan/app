package com.amw.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Gathers information about person.
 */
@Entity
public class Person {

    @Id
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
