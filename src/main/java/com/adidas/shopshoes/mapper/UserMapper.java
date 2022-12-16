package com.adidas.shopshoes.mapper;

import com.adidas.shopshoes.dto.UserDTO;
import com.adidas.shopshoes.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(UserDTO userDTO);

    UserDTO toUserDTO(User user);

    List<UserDTO> toUserDTOs(List<User> users);
}
