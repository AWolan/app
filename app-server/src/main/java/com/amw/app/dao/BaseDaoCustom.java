package com.amw.app.dao;

import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseDaoCustom<T, K extends Serializable> extends JpaRepository<T, K> {
    JPAQuery<T> query();
}
