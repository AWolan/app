package com.amw.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.io.Serializable;

/**
 * .
 */
public interface BaseDao<T, K extends Serializable> extends JpaRepository<T, K>, QueryDslPredicateExecutor<T> {
}
