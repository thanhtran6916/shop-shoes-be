package com.adidas.shopshoes.service;

import com.adidas.shopshoes.dto.RoleDTO;
import com.adidas.shopshoes.mapper.RoleMapper;
import com.adidas.shopshoes.model.Role;
import com.adidas.shopshoes.repository.RoleRepository;

import java.util.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        Role role = roleMapper.toRole(roleDTO);
        role = roleRepository.save(role);
        return roleMapper.toRoleDTO(role);
    }

    @Override
    public RoleDTO update(RoleDTO roleDTO) {
        if (Objects.isNull(roleDTO.getId())) return null;
        Optional<Role> roleOptional = roleRepository.findById(roleDTO.getId());
        if (roleOptional.isEmpty()) return null;
        Role role = roleRepository.save(roleMapper.toRole(roleDTO));
        return roleMapper.toRoleDTO(role);
    }

    @Override
    public RoleDTO findById(String id) {
        if (Objects.isNull(id)) return null;
        Optional<Role> roleOptional = roleRepository.findById(id);
        return roleOptional.map(roleMapper::toRoleDTO).orElse(null);
    }

    @Override
    public List<RoleDTO> findAll() {
        List<Role> roles = roleRepository.findAll();
        if (CollectionUtils.isEmpty(roles)) return Collections.emptyList();
        return roleMapper.toRoleDTOs(roles);
    }

    @Override
    public Set<RoleDTO> findAllSetRole() {
        List<RoleDTO> roles = findAll();
        return new HashSet<>(roles);
    }
}
