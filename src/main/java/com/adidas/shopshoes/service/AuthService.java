package com.adidas.shopshoes.service;

import com.adidas.shopshoes.dto.BaseResponse;
import com.adidas.shopshoes.dto.UserDTO;

public interface AuthService {

    BaseResponse login(UserDTO userDTO);
}
