package com.adidas.shopshoes.service;

import com.adidas.shopshoes.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends GeneralService<UserDTO>, UserDetailsService {
    List<UserDTO> getAll2();
}
