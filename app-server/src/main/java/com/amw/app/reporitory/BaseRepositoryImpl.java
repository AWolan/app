package com.amw.app.reporitory;

import com.amw.app.dao.BaseDao;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BaseRepositoryImpl<T, K extends Serializable> {

    @Autowired
    protected BaseDao<T, K> dao;
    @Autowired
    private EntityManager entityManager;

    protected List<T> getList(Predicate expression) {
        Iterable<T> all = dao.findAll(expression);
        return StreamSupport.stream(all.spliterator(), false)
                .collect(Collectors.toList());
    }

    protected JPAQuery<T> query() {
        return new JPAQuery<>(entityManager);
    }

}
