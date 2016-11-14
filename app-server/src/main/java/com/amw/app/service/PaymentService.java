package com.amw.app.service;

import com.amw.app.model.Payment;

import java.util.List;

/**
 * Manages payment actions.
 */
public interface PaymentService {


    List<Payment> getListBy(Long filterId);
}
