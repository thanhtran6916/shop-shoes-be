package com.adidas.shopshoes.mapper;

import com.adidas.shopshoes.dto.ShoesDTO;
import com.adidas.shopshoes.dto.request.ShoesRequest;
import com.adidas.shopshoes.model.Shoes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ShoesMapper {

    ShoesMapper INSTANCE = Mappers.getMapper(ShoesMapper.class);

    Shoes toShoes(ShoesDTO shoesDTO);

    ShoesDTO toShoesDTO(Shoes shoes);

    List<Shoes> toListShoes(List<ShoesDTO> shoesDTOS);

    List<ShoesDTO> toShoesDTOs(List<Shoes> shoesList);

    ShoesDTO shoesRequestToShoesDTO(ShoesRequest shoesRequest);
}
