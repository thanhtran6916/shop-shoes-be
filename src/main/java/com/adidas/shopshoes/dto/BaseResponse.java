package com.adidas.shopshoes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponse {
    private String message = "Ok";
    private String errorCode = "0";
    private Object data;
    private Integer totalCount;
}
