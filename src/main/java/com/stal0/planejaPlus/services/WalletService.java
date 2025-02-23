package com.stal0.planejaPlus.services;

import com.stal0.planejaPlus.dto.UserDTO;
import com.stal0.planejaPlus.dto.WalletDTO;
import com.stal0.planejaPlus.entities.User;
import com.stal0.planejaPlus.entities.Wallet;
import com.stal0.planejaPlus.repositories.UserRepository;
import com.stal0.planejaPlus.repositories.WalletRepository;
import com.stal0.planejaPlus.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<WalletDTO> findAll() {
        List<Wallet> list = walletRepository.findAll();
        return list.stream().map(x -> new WalletDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public WalletDTO findById(Long id) {
        Optional<Wallet> wallet = walletRepository.findById(id);
        return new WalletDTO(wallet.orElseThrow(() -> new ResourceNotFoundException("Wallet not found")));
    }

    @Transactional(readOnly = true)
    public List<WalletDTO> findByUser(UserDTO userDTO) {
        try {

            User user = userRepository.getReferenceById(userDTO.getId());
            List<Wallet> list = walletRepository.findByUser(user);
            return list.stream().map(x -> new WalletDTO(x)).toList();
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Transactional
    public WalletDTO createWallet(WalletDTO walletDTO, UserDTO userDTO) {
        try {

            Wallet wallet = new Wallet();
            wallet.setName(walletDTO.getName());
            wallet.setBalance(walletDTO.getBalance());
            wallet.setUser(userRepository.getReferenceById(userDTO.getId()));
            walletRepository.save(wallet);
            return new WalletDTO(wallet);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Transactional
    public WalletDTO updateWallet(WalletDTO walletDTO) {
        try {
            Wallet wallet = walletRepository.getReferenceById(walletDTO.getId());
            wallet.setName(walletDTO.getName());
            wallet.setBalance(walletDTO.getBalance());
            walletRepository.save(wallet);
            return new WalletDTO(wallet);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Wallet not found");
        }
    }

    @Transactional
    public void deleteWallet(Long id) {
        walletRepository.deleteById(id);
    }

}
