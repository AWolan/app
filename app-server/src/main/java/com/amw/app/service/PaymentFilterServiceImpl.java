package com.amw.app.service;

import com.amw.app.model.PaymentFilter;
import com.amw.app.reporitory.PaymentFilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentFilterServiceImpl implements PaymentFilterService {

    @Autowired
    private PaymentFilterRepository paymentFilterRepository;

    @Override
    public PaymentFilter getById(Long id) {
        return paymentFilterRepository.findOne(id);
    }

    @Override
    public PaymentFilter saveFilter(Long ownerId, PaymentFilter filter) {
        return paymentFilterRepository.save(filter);
    }

    @Override
    public List<PaymentFilter> getList(Long ownerId) {
        return paymentFilterRepository.findByOwnerId(ownerId);
    }

}
