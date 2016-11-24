package com.amw.app.builder;

import com.amw.app.exception.BuilderException;
import com.amw.app.model.Name;
import com.amw.app.model.Person;

/**
 * Builder for person.
 */
public class PersonBuilder extends BaseBuilder<Person> {

    private AccountBuilder accountBuilder;
    private NameBuilder nameBuilder;

    private Long id;
    private Name name;

    public PersonBuilder() {
        super(new Person());
    }

    public PersonBuilder(AccountBuilder accountBuilder) {
        super(new Person());
        this.accountBuilder = accountBuilder;
    }

    public PersonBuilder id(Long id) {
        get().setId(id);
        return this;
    }

    public NameBuilder name() {
        nameBuilder = new NameBuilder(this);
        return nameBuilder;
    }

//    public PersonBuilder name(Name name) {
//        get().setName(name);
//        return this;
//    }

    public AccountBuilder backToAccount() throws BuilderException {
        if (accountBuilder != null) {
            return accountBuilder;
        } else {
            throw new BuilderException("Cannot return to account builder");
        }
    }

    public Person build() throws BuilderException {
        Person person = get();

//        if (nameBuilder != null) {
//            Name name = nameBuilder.build();
//            person.setName(name);
//        }
//
//        if (person.getName() == null) {
//            throw new BuilderException("Person: Name is not present");
//        }

        return person;
    }
}
