package com.amw.app.service;

import com.amw.app.dto.PaymentDTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Manages payment actions.
 */
public interface PaymentService {


//    List<Payment> getListBy(Long filterId);

    List<PaymentDTO> getListBy(Long filterId, LocalDateTime from, LocalDateTime to);
}
