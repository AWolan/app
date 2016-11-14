package com.amw.app.reporitory;

import com.amw.app.model.PaymentFilter;

public class PaymentFilterRepositoryImpl extends BaseRepositoryImpl<PaymentFilter, Long> implements PaymentFilterRepository {


    @Override
    public PaymentFilter findOne(Long id) {
        return dao.findOne(id);
    }

}
