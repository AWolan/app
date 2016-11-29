package com.amw.app.reporitory;

import com.amw.app.dao.PaymentDao;
import com.amw.app.enums.PaymentDirection;
import com.amw.app.enums.PaymentStatus;
import com.amw.app.model.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.amw.app.enums.PaymentDirection.INCOMING;
import static com.amw.app.enums.PaymentDirection.OUTGOING;
import static com.amw.app.enums.PaymentStatus.EXECUTED;
import static com.amw.app.enums.PaymentStatus.PLANNED;

@Service
public class PaymentRepositoryImpl implements PaymentRepository {

    private QPayment payment = QPayment.payment;
    private QPaymentFilter paymentFilter = QPaymentFilter.paymentFilter;

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public List<Payment> getListBy(PaymentFilter filter, LocalDateTime from, LocalDateTime to) {
        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression isOwner = isOwner(filter.getOwnerId(), filter.getDirection());
        BooleanExpression isDate = isDate(filter.getStatus(), from, to);
//        BooleanBuilder isCategory = isCategory(filter.getCategoryList());

        builder.and(isOwner)
                .and(isDate);
//                .and(isCategory);

//        if (filter.isDatSetted()) {
//            BooleanExpression isDate = isDate(filter.getStatus(), filter.getFrom(), filter.getTo());
//            builder.and(isDate);
//        }
        if (filter.isCategoryListSetted()) {
            BooleanBuilder isCategory = isCategory(filter.getCategoryList());
            builder.and(isCategory);
        }

        return paymentDao.query()
                .select(payment)
                .from(payment)
                .where(builder)
                .fetch();
    }

    @Override
    public List<Payment> getListBy(Long filterId, LocalDateTime from, LocalDateTime to) {
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(paymentFilter.id.eq(filterId))
                .and(isOwner())
                .and(isDate(from, to));
//                .and(isCategory());

        return paymentDao.query()
                .select(payment)
                .from(payment, paymentFilter)
                .where(builder)
                .fetch();
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

    private BooleanExpression isOwner() {
        BooleanExpression isSource = payment.source.owner.id.eq(paymentFilter.ownerId);
        BooleanExpression isTarget = payment.target.owner.id.eq(paymentFilter.ownerId);

        return paymentFilter.direction
                .when(INCOMING).then(isTarget)
                .when(OUTGOING).then(isSource)
                .otherwise(isSource.or(isTarget));
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

    private BooleanExpression isDate(LocalDateTime from, LocalDateTime to) {
        BooleanExpression isPlanned = isDate(payment.planDate, from, to);
        BooleanExpression isExecuted = isDate(payment.executionDate, from, to);

        return paymentFilter.status
                .when(PLANNED).then(isPlanned)
                .when(EXECUTED).then(isExecuted)
                .otherwise(isPlanned.or(isExecuted));
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

    private BooleanExpression isCategory() {
        return payment.categoryList.any()
                .in(paymentFilter.categoryList);
    }

}
