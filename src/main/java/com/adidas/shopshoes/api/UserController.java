package com.adidas.shopshoes.api;

import com.adidas.shopshoes.anotation.IsViewer;
import com.adidas.shopshoes.dto.BaseResponse;
import com.adidas.shopshoes.dto.UserDTO;
import com.adidas.shopshoes.service.UserService;
import com.adidas.shopshoes.util.Constant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping
//    @Secured("ROLE_ADMIN")
//    @RolesAllowed("ROLE_ADMIN")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    @PreAuthorize("hasPermission()")
//    @PostAuthorize("returnObject.body.data == authentication.principal.username")
//    @IsViewer
    @PreAuthorize("hasPermission('USER', 'WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> getAllUsers() {
        BaseResponse response = new BaseResponse();
        List<UserDTO> userDTOS = userService.findAll();
        response.setData(userDTOS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> getById(@PathVariable String id) {
        BaseResponse response = new BaseResponse();
        UserDTO userDTO = userService.findById(id);
        response.setData(userDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> insertUser(@Valid @RequestBody UserDTO userDTO) {
        BaseResponse response = new BaseResponse();
        userDTO = userService.save(userDTO);
        if (Objects.isNull(userDTO)) {
            response.setMessage(Constant.MESSAGE_ERROR);
            response.setErrorCode(Constant.ERROR_CODE);
        }
        response.setData(userDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<BaseResponse> updateUser(@RequestBody UserDTO userDTO) {
        BaseResponse response = new BaseResponse();
        userDTO = userService.update(userDTO);
        if (Objects.isNull(userDTO)) {
            response.setMessage(Constant.MESSAGE_ERROR);
            response.setErrorCode(Constant.ERROR_CODE);
        }
        response.setData(userDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
