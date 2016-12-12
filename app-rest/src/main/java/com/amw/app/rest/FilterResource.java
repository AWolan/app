package com.amw.app.rest;

import com.amw.app.model.PaymentFilter;
import com.amw.app.service.PaymentFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("filter")
public class FilterResource {

    @Autowired
    private PaymentFilterService paymentFilterService;

    @GetMapping(value = "payment", produces = APPLICATION_JSON_VALUE)
    public List<PaymentFilter> getList(HttpServletRequest request) {
        Long ownerId = 1l;
        // request.getUserPrincipal().getName();
        return paymentFilterService.getList(ownerId);
    }

}
