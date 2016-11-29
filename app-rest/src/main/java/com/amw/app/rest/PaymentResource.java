package com.amw.app.rest;

import com.amw.app.model.Payment;
import com.amw.app.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("payment")
public class PaymentResource {

    @Autowired
    private PaymentService paymentService;

    @GetMapping(value = "filter/{filterId}/{from}/{to}", produces = APPLICATION_JSON_VALUE)
    public List<Payment> getList(@PathVariable("filterId") Long filterId,
                                 @PathVariable("from") @DateTimeFormat(pattern = "yyyy-MM-dd=HH:mm") LocalDateTime from,
                                 @PathVariable("to") @DateTimeFormat(pattern = "yyyy-MM-dd=HH:mm") LocalDateTime to) {
        return paymentService.getListBy(filterId, from, to);
    }

}
