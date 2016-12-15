package com.amw.app.rest;

import com.amw.app.dto.CreditDTO;
import com.amw.app.dto.InstallmentDTO;
import com.amw.app.dto.InsuranceDTO;
import com.amw.app.enums.PaymentPeriod;
import com.amw.app.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("credit")
public class CreditResource {

    @Autowired
    private CreditService creditService;

    @GetMapping(value = "epekao/{amount}/{duration}", produces = APPLICATION_JSON_VALUE)
    public CreditDTO getEPekao(@PathVariable("amount") Double amount,
                              @PathVariable("duration") Integer duration) {
        Double ownPercent = 10.0;
        Double interest = 3.63;
        Double bridgingInterest = 1.0;
        Integer bridgingDuration = 6;
        InsuranceDTO insurance1 = insurance("Ubezpieczenie niskiego wkładu", 0.06, (1.0 - ownPercent / 100.0) * amount, PaymentPeriod.ONE_TIME, duration);
        InsuranceDTO insurance2 = insurance("Ubezpieczenie na życie", 0.3, (1.0 - ownPercent / 100.0) * amount, PaymentPeriod.ANNUALLY, duration);
        InsuranceDTO insurance3 = insurance("Ubezpieczenie nieruchomości", 0.1, amount, PaymentPeriod.ANNUALLY, duration);
        List<InsuranceDTO> insuranceList = Arrays.asList(insurance1, insurance2, insurance3);

        CreditDTO credit = new CreditDTO();
        credit.setValue(amount);
        credit.setOwnPercent(ownPercent);
        credit.setInterest(interest);
        credit.setDuration(duration);
        credit.setBridgingInterest(bridgingInterest);
        credit.setBridgingDuration(bridgingDuration);
        credit.setInsuranceList(insuranceList);

        return creditService.generateEqual(credit);
    }

    @GetMapping(value = "eraiffeisen/{amount}/{duration}", produces = APPLICATION_JSON_VALUE)
    public CreditDTO getERaiffeisen(@PathVariable("amount") Double amount,
                                   @PathVariable("duration") Integer duration) {
        Double ownPercent = 10.0;
        Double interest = 4.13;
        Double bridgingInterest = 1.5;
        Integer bridgingDuration = 6;
        InsuranceDTO insurance1 = insurance("Ubezpieczenie na życie", 0.04, (1.0 - ownPercent / 100.0) * amount, PaymentPeriod.MONTHLY, duration);
        InsuranceDTO insurance2 = insurance("Ubezpieczenie nieruchomości", 0.0065, amount, PaymentPeriod.MONTHLY, duration);
        List<InsuranceDTO> insuranceList = Arrays.asList(insurance1, insurance2);

        CreditDTO credit = new CreditDTO();
        credit.setValue(amount);
        credit.setOwnPercent(ownPercent);
        credit.setInterest(interest);
        credit.setDuration(duration);
        credit.setBridgingInterest(bridgingInterest);
        credit.setBridgingDuration(bridgingDuration);
        credit.setInsuranceList(insuranceList);

        return creditService.generateEqual(credit);
    }
    @GetMapping(value = "ebzw/{amount}/{duration}", produces = APPLICATION_JSON_VALUE)
    public CreditDTO getEBzw(@PathVariable("amount") Double amount,
                            @PathVariable("duration") Integer duration) {
        Double ownPercent = 10.0;
        Double interest = 4.22;
        Double bridgingInterest = 1.0;
        Integer bridgingDuration = 6;
        InsuranceDTO insurance = insurance("Ubezpieczenie nieruchomości", 0.1, amount, PaymentPeriod.ANNUALLY, duration);
        List<InsuranceDTO> insuranceList = Collections.singletonList(insurance);

        CreditDTO credit = new CreditDTO();
        credit.setValue(amount);
        credit.setOwnPercent(ownPercent);
        credit.setInterest(interest);
        credit.setDuration(duration);
        credit.setBridgingInterest(bridgingInterest);
        credit.setBridgingDuration(bridgingDuration);
        credit.setInsuranceList(insuranceList);

        return creditService.generateEqual(credit);
    }

    @GetMapping(value = "pekao/{amount}/{duration}", produces = APPLICATION_JSON_VALUE)
    public CreditDTO getPekao(@PathVariable("amount") Double amount,
                              @PathVariable("duration") Integer duration) {
        Double ownPercent = 10.0;
        Double interest = 3.63;
        Double bridgingInterest = 1.0;
        Integer bridgingDuration = 6;
        InsuranceDTO insurance1 = insurance("Ubezpieczenie niskiego wkładu", 0.06, (1.0 - ownPercent / 100.0) * amount, PaymentPeriod.ONE_TIME, duration);
        InsuranceDTO insurance2 = insurance("Ubezpieczenie na życie", 0.3, (1.0 - ownPercent / 100.0) * amount, PaymentPeriod.ANNUALLY, duration);
        InsuranceDTO insurance3 = insurance("Ubezpieczenie nieruchomości", 0.1, amount, PaymentPeriod.ANNUALLY, duration);
        List<InsuranceDTO> insuranceList = Arrays.asList(insurance1, insurance2, insurance3);

        CreditDTO credit = new CreditDTO();
        credit.setValue(amount);
        credit.setOwnPercent(ownPercent);
        credit.setInterest(interest);
        credit.setDuration(duration);
        credit.setBridgingInterest(bridgingInterest);
        credit.setBridgingDuration(bridgingDuration);
        credit.setInsuranceList(insuranceList);

        return creditService.generateDsc(credit);
    }

    @GetMapping(value = "raiffeisen/{amount}/{duration}", produces = APPLICATION_JSON_VALUE)
    public CreditDTO getRaiffeisen(@PathVariable("amount") Double amount,
                                   @PathVariable("duration") Integer duration) {
        Double ownPercent = 10.0;
        Double interest = 4.13;
        Double bridgingInterest = 1.5;
        Integer bridgingDuration = 6;
        InsuranceDTO insurance1 = insurance("Ubezpieczenie na życie", 0.04, (1.0 - ownPercent / 100.0) * amount, PaymentPeriod.MONTHLY, duration);
        InsuranceDTO insurance2 = insurance("Ubezpieczenie nieruchomości", 0.0065, amount, PaymentPeriod.MONTHLY, duration);
        List<InsuranceDTO> insuranceList = Arrays.asList(insurance1, insurance2);

        CreditDTO credit = new CreditDTO();
        credit.setValue(amount);
        credit.setOwnPercent(ownPercent);
        credit.setInterest(interest);
        credit.setDuration(duration);
        credit.setBridgingInterest(bridgingInterest);
        credit.setBridgingDuration(bridgingDuration);
        credit.setInsuranceList(insuranceList);

        return creditService.generateDsc(credit);
    }
    @GetMapping(value = "bzw/{amount}/{duration}", produces = APPLICATION_JSON_VALUE)
    public CreditDTO getBzw(@PathVariable("amount") Double amount,
                            @PathVariable("duration") Integer duration) {
        Double ownPercent = 10.0;
        Double interest = 4.22;
        Double bridgingInterest = 1.0;
        Integer bridgingDuration = 6;
        InsuranceDTO insurance = insurance("Ubezpieczenie nieruchomości", 0.1, amount, PaymentPeriod.ANNUALLY, duration);
        List<InsuranceDTO> insuranceList = Collections.singletonList(insurance);

        CreditDTO credit = new CreditDTO();
        credit.setValue(amount);
        credit.setOwnPercent(ownPercent);
        credit.setInterest(interest);
        credit.setDuration(duration);
        credit.setBridgingInterest(bridgingInterest);
        credit.setBridgingDuration(bridgingDuration);
        credit.setInsuranceList(insuranceList);

        return creditService.generateDsc(credit);
    }

    @PostMapping(value = "dsc", produces = APPLICATION_JSON_VALUE)
    public CreditDTO getDescending(@RequestBody CreditDTO credit) {
        return creditService.generateDsc(credit);
    }

    private InsuranceDTO insurance(String name, Double percent, Double baseAmount, PaymentPeriod period, Integer duration) {
        InsuranceDTO insuranceDTO = new InsuranceDTO();

        insuranceDTO.setName(name);
        insuranceDTO.setPercent(percent);
        insuranceDTO.setBaseAmount(baseAmount);
        insuranceDTO.setAmount(percent / 100.0 * baseAmount);
        insuranceDTO.setPeriod(period);
        insuranceDTO.setDuration(duration);

        return insuranceDTO;
    }

}
