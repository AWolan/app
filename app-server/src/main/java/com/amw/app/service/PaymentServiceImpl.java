package com.amw.app.service;

import com.amw.app.model.Payment;
import com.amw.app.model.PaymentFilter;
import com.amw.app.reporitory.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentFilterService paymentFilterService;

    @Override
    public List<Payment> getListBy(Long filterId, LocalDateTime from, LocalDateTime to) {
        PaymentFilter filter = paymentFilterService.getById(filterId);

        Assert.notNull(filter, "Filter not found.");

        return paymentRepository.getListBy(filter, from, to);
    }

//    @Override
//    public List<Payment> getListBy(Long filterId, LocalDateTime from, LocalDateTime to) {
//        return paymentRepository.getListBy(filterId, from, to);
//    }

}
