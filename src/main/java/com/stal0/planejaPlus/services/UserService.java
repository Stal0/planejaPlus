package com.stal0.planejaPlus.services;

import com.stal0.planejaPlus.dto.UserDTO;
import com.stal0.planejaPlus.entities.User;
import com.stal0.planejaPlus.repositories.UserRepository;
import com.stal0.planejaPlus.services.exceptions.DataBaseException;
import com.stal0.planejaPlus.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user = userRepository.save(user);
        return new UserDTO(user);
    }

    @Transactional
    public UserDTO updateUser(long id, UserDTO userDTO) {
        try {
            User entity = userRepository.getReferenceById(id);
            copyDtoToEntity(userDTO, entity);
            entity = userRepository.save(entity);
            return new UserDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Entity not found!");
        }
    }

    @Transactional
    public void deleteUser(long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Entity not found!");
        }
        try {
            userRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Failure of referential integrity");
        }
    }

    private void copyDtoToEntity(UserDTO userDTO, User entity) {
        entity.setUsername(userDTO.getUsername());
        entity.setEmail(userDTO.getEmail());
    }
}
