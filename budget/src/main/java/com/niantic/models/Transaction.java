package com.niantic.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
    private int transactionId;
    private int userId;
    private int categoryId;
    private int vendorId;
    private LocalDate transactionDate;
    private BigDecimal amount;
    private String notes;

    public Transaction (){}

    public Transaction(int transactionId, int userId, int categoryId, int vendorId, LocalDate transactionDate, BigDecimal amount, String notes) {
        this.userId = userId;
        this.transactionId = transactionId;
        this.categoryId = categoryId;
        this.vendorId = vendorId;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.notes = notes;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
