package com.niantic.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
    private int transactionId;
    private int userId;
    private int subCategoryId;
    private int vendorId;
    private LocalDate transactionDate;
    private BigDecimal amount;

    public Transaction() {}

    public Transaction(int transactionId, int userId, int subCategoryId, int vendorId, LocalDate transactionDate, BigDecimal amount)
    {
        this.transactionId = transactionId;
        this.userId = userId;
        this.subCategoryId = subCategoryId;
        this.vendorId = vendorId;
        this.transactionDate = transactionDate;
        this.amount = amount;
    }

    public int getTransactionId() {return transactionId;}

    public void setTransactionId(int transactionId) {this.transactionId = transactionId;}

    public int getUserId() {return userId;}

    public void setUserId(int userId) {this.userId = userId;}

    public int getSubCategoryId() {return subCategoryId;}

    public void setSubCategoryId(int subCategoryId) {this.subCategoryId = subCategoryId;}

    public int getVendorId() {return vendorId;}

    public void setVendorId(int vendorId) {this.vendorId = vendorId;}

    public LocalDate getTransactionDate() {return transactionDate;}

    public void setTransactionDate(LocalDate transactionDate) {this.transactionDate = transactionDate;}

    public BigDecimal getAmount() {return amount;}

    public void setAmount(BigDecimal amount) {this.amount = amount;}

    @Override
    public String toString() {
        return String.format("%-5d %-15tF %-8d", transactionId, transactionDate, amount);
    }
}
