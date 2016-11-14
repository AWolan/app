package com.amw.app.service;

import com.amw.app.model.PaymentFilter;

public interface PaymentFilterService {
    PaymentFilter getById(Long id);

    PaymentFilter createFilter(PaymentFilter filter);
}
