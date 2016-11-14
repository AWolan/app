package com.amw.app.service;

import com.amw.app.model.Payment;
import com.amw.app.model.PaymentFilter;
import com.amw.app.reporitory.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentFilterService paymentFilterService;

    public List<Payment> getListBy(Long filterId) {
        PaymentFilter filter = paymentFilterService.getById(filterId);

        return paymentRepository.getListBy(filter);
    }

}
