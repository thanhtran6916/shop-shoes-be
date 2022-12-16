package com.adidas.shopshoes.api;

import com.adidas.shopshoes.dto.BaseResponse;
import com.adidas.shopshoes.exception.CustomCodeException;
import com.adidas.shopshoes.service.FileService;
import com.adidas.shopshoes.util.Constant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    @GetMapping("/images/{folder}/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String folder, @PathVariable String id) {
        byte[] bytes = fileService.readFileImage(folder, id);
        if (Objects.isNull(bytes)) {
            throw new CustomCodeException(BaseResponse.builder()
                    .message(Constant.MESSAGE_ERROR)
                    .errorCode(Constant.ERROR_CODE)
                    .build());
        }
        return new ResponseEntity<>(bytes, HttpStatus.OK);
    }
}
