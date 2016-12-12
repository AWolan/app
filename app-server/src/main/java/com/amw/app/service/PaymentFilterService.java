package com.amw.app.service;

import com.amw.app.model.PaymentFilter;

import java.util.List;

public interface PaymentFilterService {
    PaymentFilter getById(Long id);

    PaymentFilter saveFilter(PaymentFilter filter);

    List<PaymentFilter> getList(Long ownerId);
}
