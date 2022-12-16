package com.adidas.shopshoes.api;

import com.adidas.shopshoes.dto.BaseResponse;
import com.adidas.shopshoes.dto.UserDTO;
import com.adidas.shopshoes.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestBody UserDTO userDTO) {
        BaseResponse response = authService.login(userDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
