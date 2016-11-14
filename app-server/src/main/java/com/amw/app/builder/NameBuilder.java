package com.amw.app.builder;

import com.amw.app.exception.BuilderException;
import com.amw.app.model.Name;

/**
 * Builder for name.
 */
public class NameBuilder extends BaseBuilder<Name> {

    private PersonBuilder personBuilder;

    public NameBuilder(PersonBuilder personBuilder) {
        super(new Name());
        this.personBuilder = personBuilder;
    }

    public PersonBuilder backToPerson() throws BuilderException {
        if (personBuilder != null) {
            return personBuilder;
        } else {
            throw new BuilderException("Cannot return to person builder");
        }
    }

    public Name build() {
        return get();
    }
}
