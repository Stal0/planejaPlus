package com.stal0.planejaPlus.repositories;

import com.stal0.planejaPlus.entities.User;
import com.stal0.planejaPlus.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    List<Wallet> findByUser(User user);

}
