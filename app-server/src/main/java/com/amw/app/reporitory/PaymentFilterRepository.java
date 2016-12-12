package com.amw.app.reporitory;

import com.amw.app.model.PaymentFilter;

import java.util.List;

public interface PaymentFilterRepository {
    PaymentFilter findOne(Long id);

    PaymentFilter save(PaymentFilter filter);

    List<PaymentFilter> findByOwnerId(Long ownerId);
}
