package com.stal0.planejaPlus.dto;

import com.stal0.planejaPlus.entities.Transaction;
import com.stal0.planejaPlus.entities.Wallet;
import com.stal0.planejaPlus.entities.enums.TransactionType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class TransactionDTO {

    private Long id;
    private String description;
    private Double amount;
    private TransactionType type;
    private LocalDateTime date;
    private Wallet wallet;

    public TransactionDTO() {}

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.description = transaction.getDescription();
        this.amount = transaction.getAmount();
        this.type = transaction.getType();
        this.date = transaction.getDate();
        this.wallet = transaction.getWallet();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
