package com.amw.app.service;

import com.amw.app.model.PaymentFilter;
import com.amw.app.reporitory.PaymentFilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentFilterServiceImpl implements PaymentFilterService {

    @Autowired
    private PaymentFilterRepository paymentFilterRepository;

    @Override
    public PaymentFilter getById(Long id) {
        return paymentFilterRepository.findOne(id);
    }

}
