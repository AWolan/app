package com.amw.app.reporitory;

import com.amw.app.enums.PaymentDirection;
import com.amw.app.enums.PaymentStatus;
import com.amw.app.model.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;

import java.time.LocalDateTime;
import java.util.List;

public class PaymentRepositoryImpl extends BaseRepositoryImpl<Payment, Long> implements PaymentRepository {

    private QPayment payment = QPayment.payment;

    @Override
    public List<Payment> getListBy(PaymentFilter filter) {
        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression isOwner = isOwner(filter.getOwnerId(), filter.getDirection());
        builder.and(isOwner);

//        if (filter.isDatSetted()) {
//            BooleanExpression isDate = isDate(filter.getStatus(), filter.getFrom(), filter.getTo());
//            builder.and(isDate);
//        }
        if (filter.isCategoryListSetted()) {
            BooleanBuilder isCategory = isCategory(filter.getCategoryList());
            builder.and(isCategory);
        }


        return getList(builder);
    }

    private BooleanExpression isOwner(Long ownerId, PaymentDirection direction) {
        BooleanExpression isSource = payment.source.owner.id.eq(ownerId);
        BooleanExpression isTarget = payment.target.owner.id.eq(ownerId);

        switch (direction) {
            case INCOMING:
                return isTarget;
            case OUTGOING:
                return isSource;
            case BOTH:
                return isSource.or(isTarget);
            default:
                return null;
        }
    }

    private BooleanExpression isDate(PaymentStatus status, LocalDateTime from, LocalDateTime to) {
        BooleanExpression isPayment = isDate(payment.planDate, from, to);
        BooleanExpression isExecution = isDate(payment.executionDate, from, to);

        switch (status) {
            case PLANNED:
                return isPayment;
            case EXECUTED:
                return isExecution;
            case BOTH:
                return isPayment.or(isExecution);
            default:
                return null;
        }
    }

    private BooleanExpression isDate(DateTimePath<LocalDateTime> dateTimePath, LocalDateTime from, LocalDateTime to) {
        if (from != null && to != null) {
            return dateTimePath.between(from, to);
        }else if (from != null) {
            return dateTimePath.goe(from);
        } else if (to != null) {
            return dateTimePath.loe(to);
        } else {
            return null;
        }
    }

    private BooleanBuilder isCategory(List<Category> categoryList) {
        BooleanBuilder builder = new BooleanBuilder();
        categoryList.stream()
                .forEach(category -> builder.and(payment.categoryList.contains(category)));

        return builder;
    }

}
