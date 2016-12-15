package com.amw.app.dto;


import java.util.List;

public class InstallmentDTO {

    private Double capital;
    private Double interest;
    private Double bridging;
    private List<InsuranceDTO> insuranceList;

    public InstallmentDTO() {
    }

    public Double getCapital() {
        return capital;
    }

    public void setCapital(Double capital) {
        this.capital = capital;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Double getBridging() {
        return bridging;
    }

    public void setBridging(Double bridging) {
        this.bridging = bridging;
    }

    public Double getTotal() {
        return capital + interest + bridging + getAllInsurance();
    }

    public Double getAllInsurance() {
        return insuranceList.stream()
                .map(InsuranceDTO::getAmount)
                .reduce(0.0, (sum, i) -> sum + i);
    }

    public List<InsuranceDTO> getInsuranceList() {
        return insuranceList;
    }

    public void setInsuranceList(List<InsuranceDTO> insuranceList) {
        this.insuranceList = insuranceList;
    }
}
