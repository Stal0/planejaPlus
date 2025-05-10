package com.stal0.planejaPlus.services;

import com.stal0.planejaPlus.dto.UserDTO;
import com.stal0.planejaPlus.entities.User;
import com.stal0.planejaPlus.mappers.UserMapper;
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

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    public UserDTO getUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        return userMapper.userToDTO(user.orElseThrow(() -> new ResourceNotFoundException("User not found")));
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(userMapper::userToDTO);
    }

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        userMapper.copyDtoToUser(userDTO, user);
        user = userRepository.save(user);
        return userMapper.userToDTO(user);
    }

    @Transactional
    public UserDTO updateUser(long id, UserDTO userDTO) {
        try {
            User entity = userRepository.getReferenceById(id);
            userMapper.copyDtoToUser(userDTO, entity);
            entity = userRepository.save(entity);
            return userMapper.userToDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Transactional
    public void deleteUser(long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found");
        }
        try {
            userRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Failure of referential integrity");
        }
    }
}
