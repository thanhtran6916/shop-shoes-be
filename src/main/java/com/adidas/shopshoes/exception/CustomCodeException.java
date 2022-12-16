package com.adidas.shopshoes.exception;

import com.adidas.shopshoes.dto.BaseResponse;
import lombok.Data;

@Data
public class CustomCodeException extends RuntimeException {
    private BaseResponse baseResponse;

    public CustomCodeException(BaseResponse baseResponse) {
        super();
        this.baseResponse = baseResponse;
    }
}
