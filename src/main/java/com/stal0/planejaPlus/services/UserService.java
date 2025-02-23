package com.stal0.planejaPlus.services;

import com.stal0.planejaPlus.dto.UserDTO;
import com.stal0.planejaPlus.entities.User;
import com.stal0.planejaPlus.repositories.UserRepository;
import com.stal0.planejaPlus.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDTO getUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        return new UserDTO(user.orElseThrow(() -> new ResourceNotFoundException("Entity not found!")));
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(x -> new UserDTO(x));
    }
}
