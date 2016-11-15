package com.amw.app.model;

import com.amw.app.enums.PaymentDirection;
import com.amw.app.enums.PaymentStatus;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class PaymentFilter {

    @Id
    private Long id;
    private Long ownerId;
    private PaymentDirection direction = PaymentDirection.BOTH;
    private PaymentStatus status = PaymentStatus.BOTH;
    private LocalDateTime from;
    private LocalDateTime to;
    private List<Category> categoryList;

    public PaymentFilter() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public PaymentDirection getDirection() {
        return direction;
    }

    public void setDirection(PaymentDirection direction) {
        this.direction = direction;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    public boolean isDatSetted() {
        return from != null || to != null;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public boolean isCategoryListSetted() {
        return categoryList != null && categoryList.size() > 0;
    }
}
