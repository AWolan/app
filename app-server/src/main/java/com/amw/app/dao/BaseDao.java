package com.amw.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * .
 */
@NoRepositoryBean
public interface BaseDao<T, K extends Serializable> extends BaseDaoCustom<T, K>, QueryDslPredicateExecutor<T> {
}
