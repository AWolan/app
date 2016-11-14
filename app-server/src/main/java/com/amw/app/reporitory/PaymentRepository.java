package com.amw.app.reporitory;

import com.amw.app.model.Payment;
import com.amw.app.model.PaymentFilter;

import java.util.List;

public interface PaymentRepository {
    List<Payment> getListBy(PaymentFilter filter);
}
