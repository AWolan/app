package com.amw.app.reporitory;

import com.amw.app.model.Payment;
import com.amw.app.model.PaymentFilter;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepository {
    List<Payment> getListBy(PaymentFilter filter, LocalDateTime from, LocalDateTime to);

    List<Payment> getListBy(Long filterId, LocalDateTime from, LocalDateTime to);
}
