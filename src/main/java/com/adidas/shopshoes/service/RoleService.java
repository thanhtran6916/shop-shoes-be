package com.adidas.shopshoes.service;

import com.adidas.shopshoes.dto.RoleDTO;

import java.util.Set;

public interface RoleService extends GeneralService<RoleDTO> {
    Set<RoleDTO> findAllSetRole();
}
