package com.stal0.planejaPlus.repositories;

import com.stal0.planejaPlus.entities.Transaction;
import com.stal0.planejaPlus.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByWallet(Wallet wallet);
    List<Transaction> findByWalletAndType(Wallet wallet, String type);
}
