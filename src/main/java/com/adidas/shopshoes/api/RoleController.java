package com.adidas.shopshoes.api;

import com.adidas.shopshoes.dto.BaseResponse;
import com.adidas.shopshoes.dto.RoleDTO;
import com.adidas.shopshoes.service.RoleService;
import com.adidas.shopshoes.util.Constant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@Slf4j
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<BaseResponse> insertRole(@RequestBody RoleDTO roleDTO) {
        BaseResponse response = new BaseResponse();
        roleDTO = roleService.save(roleDTO);
        if (Objects.isNull(roleDTO)) {
            response.setMessage(Constant.MESSAGE_ERROR);
            response.setErrorCode(Constant.ERROR_CODE);
        }
        response.setData(roleDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<BaseResponse> updateRole(@RequestBody RoleDTO roleDTO) {
        BaseResponse response = new BaseResponse();
        roleDTO = roleService.update(roleDTO);
        if (Objects.isNull(roleDTO)) {
            response.setMessage(Constant.MESSAGE_ERROR);
            response.setErrorCode(Constant.ERROR_CODE);
        }
        response.setData(roleDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> findById(@PathVariable String id) {
        BaseResponse response = new BaseResponse();
        RoleDTO roleDTO = roleService.findById(id);
        response.setData(roleDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/find-all")
    public ResponseEntity<BaseResponse> findAll() {
        BaseResponse response = new BaseResponse();
        Set<RoleDTO> roleDTOS = roleService.findAllSetRole();
        response.setData(roleDTOS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
