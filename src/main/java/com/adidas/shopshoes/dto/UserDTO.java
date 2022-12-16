package com.adidas.shopshoes.dto;

import com.adidas.shopshoes.anotation.ValidPassword;
import com.adidas.shopshoes.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private String id;
    @NotBlank(message = "Tên tài khoản không được để trống!")
    private String username;
    @NotBlank(message = "Mật khẩu tài khoản không được để trống!")
    @ValidPassword
    private String password;
    private Set<Role> roles;
    private String email;
    private String address;
    private String phoneNumber;
}
