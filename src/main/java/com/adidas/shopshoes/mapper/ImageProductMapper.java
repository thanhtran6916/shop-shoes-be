package com.adidas.shopshoes.mapper;

import com.adidas.shopshoes.dto.ImageProductDTO;
import com.adidas.shopshoes.model.ImageProduct;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ImageProductMapper {

    ImageProductMapper INSTANCE = Mappers.getMapper(ImageProductMapper.class);

    ImageProduct toImageProduct(ImageProductDTO imageProductDTO);

    ImageProductDTO toImageProductDTO(ImageProduct imageProduct);

    List<ImageProduct> toImageProductList(List<ImageProductDTO> imageProductDTOS);

    List<ImageProductDTO> toImageProductDTOList(List<ImageProduct> imageProducts);
}
