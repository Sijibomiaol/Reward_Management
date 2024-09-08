package com.Moses.CashBack_Management.dto;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;


public class CashRewardDTO {
    private BigDecimal totalCashBack;
    private BigDecimal currentBalance;
    private Long customerId;


    public CashRewardDTO() {
    }

    public CashRewardDTO(Long id, BigDecimal totalCashBack, BigDecimal currentBalance) {
        this.totalCashBack = totalCashBack;
        this.currentBalance = currentBalance;
        this.customerId = id;

    }

    public BigDecimal getTotalCashBack() {
        return totalCashBack;
    }

    public void setTotalCashBack(BigDecimal totalCashBack) {
        this.totalCashBack = totalCashBack;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "CashRewardDTO{" +
                ", totalCashBack=" + totalCashBack +
                ", currentBalance=" + currentBalance +
                ", customerId=" + customerId +
                '}';
    }
}
