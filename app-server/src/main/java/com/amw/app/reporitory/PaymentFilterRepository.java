package com.amw.app.reporitory;

import com.amw.app.model.PaymentFilter;

public interface PaymentFilterRepository {
    PaymentFilter findOne(Long id);

    PaymentFilter save(PaymentFilter filter);
}
