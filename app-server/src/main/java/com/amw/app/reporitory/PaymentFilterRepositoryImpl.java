package com.amw.app.reporitory;

import com.amw.app.dao.PaymentFilterDao;
import com.amw.app.model.PaymentFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentFilterRepositoryImpl implements PaymentFilterRepository {

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

}
