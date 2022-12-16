package com.adidas.shopshoes.mapper;

import com.adidas.shopshoes.dto.RoleDTO;
import com.adidas.shopshoes.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    Role toRole(RoleDTO roleDTO);

    RoleDTO toRoleDTO(Role role);

    List<RoleDTO> toRoleDTOs(List<Role> roles);
}
