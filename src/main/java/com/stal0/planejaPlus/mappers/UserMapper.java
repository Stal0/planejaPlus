package com.stal0.planejaPlus.mappers;

import com.stal0.planejaPlus.dto.UserDTO;
import com.stal0.planejaPlus.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToDTO(User user);

    User DtoToUser(UserDTO userDTO);

    void copyDtoToUser(UserDTO userDTO, User user);
}
