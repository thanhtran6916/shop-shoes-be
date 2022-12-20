package com.adidas.shopshoes.service;

import com.adidas.shopshoes.dto.JwtDTO;
import com.adidas.shopshoes.dto.UserPrincipal;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@Slf4j
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    @Value("${auth.expirationTime}")
    private long expirationTime;
    @Value("${auth.secret}")
    private String secret;

    @Override
    public JwtDTO createToken(UserPrincipal user) {
        String username = user.getUsername();
        String token = JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC512(secret));

        return JwtDTO.builder()
                .token("Bearer " + token)
                .expiredDate(expirationTime)
                .build();
    }

    @Override
    public String genToken(String token) {
        String authToken = token.replace("Bearer ", "");
        return JWT.require(Algorithm.HMAC512(secret))
                .build()
                .verify(authToken)
                .getSubject();
    }
}
