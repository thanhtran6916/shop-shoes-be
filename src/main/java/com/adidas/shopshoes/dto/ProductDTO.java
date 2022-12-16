package com.adidas.shopshoes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private String id;

    @NotBlank(message = "Tên sản phẩm không được để trống!")
    private String productName;

    private CategoryDTO category;

    private CustomerTypeDTO customerType;

    private Integer quantitySale;

    private Integer reviews;

    private List<ImageProductDTO> imageProduct;
}
