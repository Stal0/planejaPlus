package com.stal0.planejaPlus.services;

import com.stal0.planejaPlus.dto.TransactionDTO;
import com.stal0.planejaPlus.dto.WalletDTO;
import com.stal0.planejaPlus.entities.Transaction;
import com.stal0.planejaPlus.mappers.TransactionMapper;
import com.stal0.planejaPlus.mappers.WalletMapper;
import com.stal0.planejaPlus.repositories.TransactionRepository;
import com.stal0.planejaPlus.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {

    public final TransactionRepository repository;

    public final TransactionMapper transactionMapper;

    public final WalletMapper walletMapper;

    public TransactionService(TransactionRepository repository, TransactionMapper transactionMapper, WalletMapper walletMapper) {
        this.repository = repository;
        this.transactionMapper = transactionMapper;
        this.walletMapper = walletMapper;
    }

    @Transactional(readOnly = true)
    public List<TransactionDTO> findAllByWallet(WalletDTO walletDTO) {
        List<Transaction> transactions = repository.findByWallet(walletMapper.DtoToWallet(walletDTO));
        return transactions.stream().map(transactionMapper::transactionToDTO).toList();
    }

    @Transactional(readOnly = true)
    public TransactionDTO findById(Long id) {
        Transaction transaction = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return transactionMapper.transactionToDTO(transaction);
    }



}
