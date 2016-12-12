package com.amw.app.rest;

import com.amw.app.model.PaymentFilter;
import com.amw.app.service.PaymentFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("filter")
public class FilterResource {

    @Autowired
    private PaymentFilterService paymentFilterService;

    @GetMapping(value = "payment/{ownerId}", produces = APPLICATION_JSON_VALUE)
    public List<PaymentFilter> getList(@PathVariable("ownerId") Long ownerId, HttpServletRequest request) {
        // Long ownerId = 1l;
        // request.getUserPrincipal().getName();
        return paymentFilterService.getList(ownerId);
    }

    @PostMapping(value = "payment/{ownerId}", produces = APPLICATION_JSON_VALUE)
    public PaymentFilter savePaymentFilter(@PathVariable("ownerId") Long ownerId, @RequestBody PaymentFilter filter) {
        return paymentFilterService.saveFilter(ownerId, filter);
    }

}
