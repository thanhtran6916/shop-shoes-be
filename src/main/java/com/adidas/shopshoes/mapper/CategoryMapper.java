package com.adidas.shopshoes.mapper;

import com.adidas.shopshoes.dto.CategoryDTO;
import com.adidas.shopshoes.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toCategory(CategoryDTO categoryDTO);

    CategoryDTO toCategoryDTO(Category category);

    List<Category> toCategories(List<CategoryDTO> categoryDTOS);

    List<CategoryDTO> toCategoryDTOs(List<Category> categories);
}
