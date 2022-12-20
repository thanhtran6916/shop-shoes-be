package com.adidas.shopshoes.service;

import com.adidas.shopshoes.dto.JwtDTO;
import com.adidas.shopshoes.dto.UserPrincipal;

public interface JwtService {

    JwtDTO createToken(UserPrincipal user);

    String genToken(String token);
}
