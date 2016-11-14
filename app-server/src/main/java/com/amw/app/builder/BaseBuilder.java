package com.amw.app.builder;

import com.amw.app.exception.BuilderException;

/**
 * Base builder.
 */
public abstract class BaseBuilder<T> {

//    private BaseBuilder parentBuilder;
    private T t;

    public BaseBuilder(T t) {
        this.t = t;
    }

//    public BaseBuilder(T t, BaseBuilder parentBuilder) {
//        this.t = t;
//        this.parentBuilder = parentBuilder;
//    }

    protected T get() {
        return t;
    }

    public abstract T build() throws BuilderException;
}
