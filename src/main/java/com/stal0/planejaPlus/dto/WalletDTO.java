package com.stal0.planejaPlus.dto;

import com.stal0.planejaPlus.entities.Transaction;
import com.stal0.planejaPlus.entities.Wallet;

import java.util.ArrayList;
import java.util.List;

public class WalletDTO {

    private Long id;
    private String name;
    private Integer balance;

    private UserDTO user;

    private List<TransactionDTO> transactions = new ArrayList<>();

    public WalletDTO() {}

    public WalletDTO(Wallet wallet) {
        this.id = wallet.getId();
        this.name = wallet.getName();
        this.balance = wallet.getBalance();
        this.user = new UserDTO(wallet.getUser());
    }

    public WalletDTO(Wallet wallet, List<Transaction> transactions) {
        this(wallet);
        transactions.forEach(transaction -> this.transactions.add(new TransactionDTO(transaction)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }
}
