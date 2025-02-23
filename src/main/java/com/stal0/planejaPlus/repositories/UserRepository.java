package com.stal0.planejaPlus.repositories;

import com.stal0.planejaPlus.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
