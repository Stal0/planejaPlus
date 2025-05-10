package com.stal0.planejaPlus.services;

import com.stal0.planejaPlus.dto.UserDTO;
import com.stal0.planejaPlus.dto.WalletDTO;
import com.stal0.planejaPlus.entities.User;
import com.stal0.planejaPlus.entities.Wallet;
import com.stal0.planejaPlus.mappers.WalletMapper;
import com.stal0.planejaPlus.repositories.UserRepository;
import com.stal0.planejaPlus.repositories.WalletRepository;
import com.stal0.planejaPlus.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class WalletService {

    private final WalletRepository walletRepository;

    private final UserRepository userRepository;

    private final WalletMapper walletMapper;

    public WalletService(WalletRepository walletRepository, UserRepository userRepository, WalletMapper walletMapper) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
        this.walletMapper = walletMapper;
    }

    @Transactional(readOnly = true)
    public List<WalletDTO> findAll() {
        List<Wallet> list = walletRepository.findAll();
        return list.stream().map(walletMapper::walletToDTO).toList();
    }

    @Transactional(readOnly = true)
    public WalletDTO findById(Long id) {
        Optional<Wallet> wallet = walletRepository.findById(id);
        return walletMapper.walletToDTO(wallet.orElseThrow(() -> new ResourceNotFoundException("Wallet not found")));
    }

    @Transactional(readOnly = true)
    public List<WalletDTO> findByUser(UserDTO userDTO) {
        try {

            User user = userRepository.getReferenceById(userDTO.getId());
            List<Wallet> list = walletRepository.findByUser(user);
            return list.stream().map(walletMapper::walletToDTO).toList();
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Transactional
    public WalletDTO createWallet(WalletDTO walletDTO, UserDTO userDTO) {
        try {

            Wallet wallet = new Wallet();
            walletMapper.copyDtoToWallet(walletDTO, wallet);
            wallet.setUser(userRepository.getReferenceById(userDTO.getId()));
            walletRepository.save(wallet);
            return walletMapper.walletToDTO(wallet);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Transactional
    public WalletDTO updateWallet(WalletDTO walletDTO) {
        try {
            Wallet wallet = walletRepository.getReferenceById(walletDTO.getId());
            walletMapper.copyDtoToWallet(walletDTO, wallet);
            walletRepository.save(wallet);
            return new WalletDTO(wallet);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Wallet not found");
        }
    }

    @Transactional
    public void deleteWallet(Long id) {
        walletRepository.deleteById(id);
    }

}
