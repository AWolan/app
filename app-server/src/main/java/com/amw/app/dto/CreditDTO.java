package com.amw.app.dto;


import java.util.List;

public class CreditDTO {

    private String name;
    private Double value;
    private Double interest;
    private Double creditAmount;
    private Double totalAmount;
    private Double ownPercent;
    private Double bridgingInterest;
    private Integer duration;
    private Integer bridgingDuration;
    private boolean mdm;
    private List<InsuranceDTO> insuranceList;
    private List<InstallmentDTO> installmentList;

    public CreditDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getOwnPercent() {
        return ownPercent;
    }

    public void setOwnPercent(Double ownPercent) {
        this.ownPercent = ownPercent;
    }

    public Double getBridgingInterest() {
        return bridgingInterest;
    }

    public void setBridgingInterest(Double bridgingInterest) {
        this.bridgingInterest = bridgingInterest;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getBridgingDuration() {
        return bridgingDuration;
    }

    public void setBridgingDuration(Integer bridgingDuration) {
        this.bridgingDuration = bridgingDuration;
    }

    public boolean isMdm() {
        return mdm;
    }

    public void setMdm(boolean mdm) {
        this.mdm = mdm;
    }

    public List<InsuranceDTO> getInsuranceList() {
        return insuranceList;
    }

    public void setInsuranceList(List<InsuranceDTO> insuranceList) {
        this.insuranceList = insuranceList;
    }

    public List<InstallmentDTO> getInstallmentList() {
        return installmentList;
    }

    public void setInstallmentList(List<InstallmentDTO> installmentList) {
        this.installmentList = installmentList;
    }
}
