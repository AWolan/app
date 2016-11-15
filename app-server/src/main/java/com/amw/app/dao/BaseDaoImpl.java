package com.amw.app.dao;

import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import javax.persistence.EntityManager;
import java.io.Serializable;

public class BaseDaoImpl<T, K extends Serializable>
        extends SimpleJpaRepository<T, K> implements BaseDaoCustom<T, K> {

    private final EntityManager entityManager;

    public BaseDaoImpl(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public JPAQuery<T> query() {
        return new JPAQuery<>(entityManager);
    }

}
