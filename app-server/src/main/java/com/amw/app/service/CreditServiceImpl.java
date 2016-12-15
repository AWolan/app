package com.amw.app.service;

import com.amw.app.dto.CreditDTO;
import com.amw.app.dto.InstallmentDTO;
import com.amw.app.dto.InsuranceDTO;
import com.amw.app.enums.PaymentPeriod;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CreditServiceImpl implements CreditService {

    private static final Integer INSTALLMENT_PER_YEAR = 12;

    @Override
    public CreditDTO generateEqual(CreditDTO credit) {
        Double creditAmount = credit.getValue() * (1.0 - credit.getOwnPercent() / 100.0);

        Double q = 1.0 + credit.getInterest() / 100.0 / INSTALLMENT_PER_YEAR;
        Double qb = 1.0 + (credit.getInterest() + credit.getBridgingInterest()) / 100.0 / INSTALLMENT_PER_YEAR;

        Double base = creditAmount * Math.pow(q, credit.getDuration()) * (q - 1) / (Math.pow(q, credit.getDuration()) - 1);
        Double baseb = creditAmount * Math.pow(qb, credit.getDuration()) * (qb - 1) / (Math.pow(qb, credit.getDuration()) - 1);

        List<InstallmentDTO> installmentList = IntStream.range(0, credit.getDuration())
                .boxed()
                .map(i -> {
                    Double capitalPart = base * Math.pow(q, i - credit.getDuration());
                    Double interestPart = base - capitalPart;
                    Double bridgingPart = 0.0;

                    if (i < credit.getBridgingDuration()) {
                        bridgingPart = baseb - base;
                    }

                    List<InsuranceDTO> insuranceDTOList = getInsuranceList(credit.getInsuranceList(), i);

                    return instalment(capitalPart, interestPart, bridgingPart, insuranceDTOList);
                })
                .collect(Collectors.toList());

        Double total = installmentList.stream()
                .map(InstallmentDTO::getTotal)
                .reduce(0.0, (sum, i) -> sum + i);

        credit.setCreditAmount(creditAmount);
        credit.setInstallmentList(installmentList);
        credit.setTotalAmount(total);

        return credit;
    }

    @Override
    public CreditDTO generateDsc(CreditDTO credit) {
        Double creditAmount = credit.getValue() * (1.0 - credit.getOwnPercent() / 100.0);

        Double base = creditAmount / credit.getDuration();

        List<InstallmentDTO> installmentList = IntStream.range(0, credit.getDuration())
                .boxed()
                .map(i -> {
                    Double left = creditAmount - base * i;
                    Double interestPart = left * credit.getInterest() / 100.0 / INSTALLMENT_PER_YEAR;
                    Double bridgingPart = 0.0;

                    if (i < credit.getBridgingDuration()) {
                        bridgingPart = left * credit.getBridgingInterest() / 100.0 / INSTALLMENT_PER_YEAR;
                    }

                    List<InsuranceDTO> insuranceDTOList = getInsuranceList(credit.getInsuranceList(), i);

                    return instalment(base, interestPart, bridgingPart, insuranceDTOList);
                })
                .collect(Collectors.toList());

        Double total = installmentList.stream()
                .map(InstallmentDTO::getTotal)
                .reduce(0.0, (sum, i) -> sum + i);

        credit.setCreditAmount(creditAmount);
        credit.setInstallmentList(installmentList);
        credit.setTotalAmount(total);

        return credit;
    }

    private List<InsuranceDTO> getInsuranceList(List<InsuranceDTO> baseList, Integer i) {
        return baseList.stream()
                .filter(insurance -> is(insurance, i))
                .collect(Collectors.toList());
    }

    private boolean is(InsuranceDTO insurance, Integer i) {
        switch (insurance.getPeriod()) {
            case ONE_TIME:
                return i == 0;
            case MONTHLY:
                return i < insurance.getDuration();
            case ANNUALLY:
                return i % 12 == 0 && i < insurance.getDuration();
            default:
                return false;
        }
    }

    private void isa(InsuranceDTO insurance) {
        Double a;
        switch (insurance.getPeriod()) {
            case ONE_TIME:
                a = insurance.getBaseAmount() * insurance.getPercent() / 100.0;
                break;
            case MONTHLY:
                a = insurance.getBaseAmount() * insurance.getPercent() / 100.0 / INSTALLMENT_PER_YEAR;
                break;
            case ANNUALLY:
                a = insurance.getBaseAmount() * insurance.getPercent() / 100.0;
                break;
            default:
                a = null;
        }
        insurance.setAmount(a);
    }

    private InstallmentDTO instalment(Double capital, Double interest, Double bridging, List<InsuranceDTO> insuranceList) {
        InstallmentDTO installmentDTO = new InstallmentDTO();

        installmentDTO.setCapital(capital);
        installmentDTO.setInterest(interest);
        installmentDTO.setBridging(bridging);
        installmentDTO.setInsuranceList(insuranceList);

        return installmentDTO;
    }

    private InsuranceDTO insurance(String name, Double percent, Double baseAmount, PaymentPeriod period, Integer duration) {
        InsuranceDTO insuranceDTO = new InsuranceDTO();

        insuranceDTO.setName(name);
        insuranceDTO.setPercent(percent);
        insuranceDTO.setBaseAmount(baseAmount);
        insuranceDTO.setPeriod(period);
        insuranceDTO.setDuration(duration);

        return insuranceDTO;
    }

}
