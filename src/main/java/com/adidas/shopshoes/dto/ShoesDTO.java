package com.adidas.shopshoes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoesDTO extends ProductDTO {

    @NotNull(message = "Size giầy không được để trống!")
    private Integer size;

    private String color;
}
