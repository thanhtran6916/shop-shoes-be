package com.adidas.shopshoes.api;

import com.adidas.shopshoes.dto.BaseResponse;
import com.adidas.shopshoes.dto.ImageProductDTO;
import com.adidas.shopshoes.dto.ShoesDTO;
import com.adidas.shopshoes.dto.request.ShoesRequest;
import com.adidas.shopshoes.service.FileService;
import com.adidas.shopshoes.service.ShoesService;
import com.adidas.shopshoes.util.Constant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/shoes")
public class ShoesController {

    private final ShoesService shoesService;
    private final FileService fileService;

    @GetMapping
    public ResponseEntity<BaseResponse> getAll() {
        BaseResponse response = new BaseResponse();
        List<ShoesDTO> shoesDTOS = shoesService.findAll();
        response.setData(shoesDTOS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> findById(@PathVariable String id) {
        BaseResponse response = new BaseResponse();
        ShoesDTO shoesDTO = shoesService.findById(id);
        response.setData(shoesDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<BaseResponse> save(@Valid @RequestBody ShoesDTO shoesDTO) {
        BaseResponse response = new BaseResponse();
        shoesDTO = shoesService.save(shoesDTO);
        if (Objects.isNull(shoesDTO)) {
            response.setData(Constant.ERROR_CODE);
            response.setMessage(Constant.MESSAGE_ERROR);
        }
        response.setData(shoesDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/insert-and-copy-file")
    public ResponseEntity<BaseResponse> saveShoesRequest(@Valid ShoesRequest shoesRequest) {
        BaseResponse response = new BaseResponse();
        ShoesDTO shoesDTO = shoesService.saveAttachedPhoto(shoesRequest);
        if (Objects.isNull(shoesDTO)) {
            response.setData(Constant.ERROR_CODE);
            response.setMessage(Constant.MESSAGE_ERROR);
        }
        response.setData(shoesDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<BaseResponse> update(@RequestBody ShoesDTO shoesDTO) {
        BaseResponse response = new BaseResponse();
        shoesDTO = shoesService.update(shoesDTO);
        if (Objects.isNull(shoesDTO)) {
            response.setData(Constant.ERROR_CODE);
            response.setMessage(Constant.MESSAGE_ERROR);
        }
        response.setData(shoesDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
