package com.amw.app.model;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;

/**
 * Gathers information about payment.
 */
@Entity
public class Payment {

    private Long id;
    private PaymentGroup group;
    private Account target;
    private Account source;
    private Double amount;
    private Currency currency;
    private String name;
    private String details;
    private LocalDateTime executionDate;
    private LocalDateTime planDate;
    private List<Category> categoryList;

    public Payment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentGroup getGroup() {
        return group;
    }

    public void setGroup(PaymentGroup group) {
        this.group = group;
    }

    public Account getTarget() {
        return target;
    }

    public void setTarget(Account target) {
        this.target = target;
    }

    public Account getSource() {
        return source;
    }

    public void setSource(Account source) {
        this.source = source;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(LocalDateTime executionDate) {
        this.executionDate = executionDate;
    }

    public LocalDateTime getPlanDate() {
        return planDate;
    }

    public void setPlanDate(LocalDateTime planDate) {
        this.planDate = planDate;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
