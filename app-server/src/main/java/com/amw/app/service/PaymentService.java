package com.amw.app.service;

import com.amw.app.model.Payment;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Manages payment actions.
 */
public interface PaymentService {


//    List<Payment> getListBy(Long filterId);

    List<Payment> getListBy(Long filterId, LocalDateTime from, LocalDateTime to);
}
