package com.amw.app.reporitory;

import com.amw.app.dao.PaymentFilterDao;
import com.amw.app.model.PaymentFilter;
import com.amw.app.model.QPaymentFilter;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentFilterRepositoryImpl implements PaymentFilterRepository {

    private QPaymentFilter paymentFilter = QPaymentFilter.paymentFilter;

    @Autowired
    private PaymentFilterDao paymentFilterDao;

    @Override
    public PaymentFilter findOne(Long id) {
        return paymentFilterDao.findOne(id);
    }

    @Override
    public PaymentFilter save(PaymentFilter filter) {
        return paymentFilterDao.save(filter);
    }

    @Override
    public List<PaymentFilter> findByOwnerId(Long ownerId) {
        BooleanExpression isOwner = paymentFilter.ownerId.eq(ownerId);

        return paymentFilterDao.query()
                .select(paymentFilter)
                .from(paymentFilter)
                .where(isOwner)
                .fetch();
    }

}
